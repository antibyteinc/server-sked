<template>
    <div class="wrapper">
        <div class="wrapper__schedule">
            <div>
                <h3 class="text-center">Розклад групи {{groupName}}</h3>
                <h4 class="text-center" style="margin-top: 20px">Період з {{dateToString(startSemester)}} по
                    {{dateToString(finishSemester)}}</h4>
            </div>
            <div class="schedule-mode text-center" style="margin-top: 20px; margin-bottom: 20px">
                <button class="btn btn-default" :disabled="!mode" @click="toggleMode">Таблица</button>
                <button class="btn btn-default" :disabled="mode" @click="toggleMode">Плитка</button>
            </div>
            <div class="schedules" v-if="!mode">
                <div class="schedules" v-if="schedules.length > 0">
                    <div class="schedule">
                        <table class="table table-condensed">
                            <thead>
                            <tr>
                                <th>id</th>
                                <th>#</th>
                                <th>Назва предмету</th>
                                <th>Назва типу предмета</th>
                                <th>Призвище викладача</th>
                                <th>Корпус</th>
                                <th>Аудиторія</th>
                                <th>Дата</th>
                                <th>Примітка</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr v-for="schedule in schedules">
                                    <td>{{schedule.id}}</td>
                                    <td>{{schedule.lessonNumber}}</td>
                                    <td>{{schedule.lessonName}}</td>
                                    <td>{{schedule.lessonType}}</td>
                                    <td>{{schedule.teacherSurname}}</td>
                                    <td>{{schedule.building}}</td>
                                    <td>{{schedule.cabinet}}</td>
                                    <td>{{dateToString(schedule.lessonDate)}}</td>
                                    <td>{{schedule.note}}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="schedules" v-else>
                    <p>Список розкладу порожній</p>
                </div>
            </div>
            <div class="schedules" v-else>
                <div class="schedule">
                    <table>
                        <tr>
                            <td v-for="week in weeks">
<!--                                <div class="week">{{week}}</div>-->
                            </td>
                        </tr>
                        <tr v-for="dayNumber in 7">
                            <td class="days" v-for="day in getWeekDayInSemester(dayNumber)">
                                <div class="day">
                                    <div class="day-header">{{dateToString(day)}}</div>
                                    <div class="day-body">
                                        <div class="lessons">
                                            <div class="lesson" v-for="lesson in getLessonsByDate(day)">
                                                <div v-if="dateToString(lesson.lessonDate) === dateToString(day)">
                                                    <div class="lesson-content" v-if="lesson.note !== 'empty'">
                                                        <div class="lesson-item" @click="">
                                                            <div class="lesson-body">
                                                                <p style="margin: 0; margin-bottom: 3px;">
                                                                    {{lesson.lessonName}}</p>
                                                                <p style="margin: 0; margin-bottom: 3px">
                                                                    {{lesson.lessonType}}</p>
                                                                <p style="margin: 0; margin-bottom: 3px">
                                                                    {{lesson.teacherSurname}}</p>
                                                                <p style="margin: 0; margin-bottom: 3px">
                                                                    {{lesson.building}} {{lesson.cabinet}}</p>
                                                                <p style="margin: 0; margin-bottom: 3px">
                                                                    {{lesson.note}}</p>
                                                            </div>
                                                        </div>
                                                        <div class="lesson-btn">
                                                        </div>
                                                    </div>
                                                    <div class="lesson-empty" v-else @click="toggleLesson(lesson.lessonDate, lesson.lessonNumber)">

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="day-footer">

                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    function getIndexItemById(list, id) {
        for (let i = 0; list.length; i++) {
            if (list[i].id === id) {
                return i;
            }
        }
        return -1;
    }

    function getWeekNumber(d) {
        d = new Date(Date.UTC(d.getFullYear(), d.getMonth(), d.getDate()));
        d.setUTCDate(d.getUTCDate() + 4 - (d.getUTCDay() || 7));
        let yearStart = new Date(Date.UTC(d.getUTCFullYear(), 0, 1));
        let weekNo = Math.ceil((((d - yearStart) / 86400000) + 1) / 7);
        return weekNo;
    }

    function getDayInWeek(year, week, day) {
        let w = week || 1;
        let n = day || 1;
        let y = year || new Date().getFullYear();
        let d = new Date(y, 0, 7 * (w - 1));
        d.setDate(d.getDate() - (d.getDay() || 7) + n);
        return d
    }

    function dateLongToString(date) {
        date = new Date(date);
        // let strMonth = ["січ", "лют", "бер", "квіт", "трав", "черв", "лип", "серп", "вер", "жовт", "лист", "груд"];

        let d = date.getDate();
        // let m = strMonth[date.getMonth()];
        let m = date.getMonth() + 1;
        let y = date.getFullYear();
        // let h = date.getHours();
        // let min = date.getMinutes();
        // let s = date.getSeconds();

        return "" + (d <= 9 ? '0' + d : d) + "." + (m <= 9 ? '0' + m : m) + "." + y;
    }

    function dateLongToStringForInput(date) {
        date = new Date(date);
        let d = date.getDate();
        let m = date.getMonth() + 1;
        let y = date.getFullYear();

        return "" + y + "-" + (m <= 9 ? '0' + m : m) + "-" + (d <= 9 ? '0' + d : d);
    }

    export default {
        props: ["groupId"],
        created() {
            this.getInstitutionIdByGroupIdFromDb();
            this.getGroupNameByGroupIdFromDb();
            this.getSemesterRangeFromDb();
            this.fillWeeks();
            this.getScheduleFromDb();
        },
        data() {
            return {
                mode: true,

                institutionId: "",
                groupName: "",
                startSemester: null,
                finishSemester: null,
                weeks: [],
                maxLessonsInDay: 5,

                //schedule
                scheduleApi: this.$resource("/schedule{/id}"),
                schedules: [],
            }
        },
        methods: {
            //from db
            getScheduleFromDb() {
                this.schedules = [];
                this.scheduleApi.get({id: this.groupId}).then(res => {
                    res.json().then(data => {
                        data.forEach(schedule => {
                            this.schedules.push(schedule);
                        })
                    })
                })
            },
            getInstitutionIdByGroupIdFromDb() {
                this.$resource("/division/get-id{/id}").get({id: this.groupId}).then(res => {
                    res.text().then(data => {
                        this.institutionId = data.valueOf();
                    });
                });
            },
            getGroupNameByGroupIdFromDb() {
                this.$resource("/group/get-name{/id}").get({id: this.groupId}).then(res => {
                    res.text().then(data => {
                        this.groupName = data;
                    });
                });
            },
            getSemesterRangeFromDb() {
                this.$resource("/group/get-semesterRange{/id}").get({id: this.groupId}).then(res => {
                    res.json().then(data => {
                        this.startSemester = data.valueOf().start;
                        this.finishSemester = data.valueOf().finish;
                    });
                });
            },

            //fill
            fillWeeks() {
                this.weeks = [];

                let start = getWeekNumber(new Date(this.startSemester));
                let end = getWeekNumber(new Date(this.finishSemester));

                for (let i = start; i <= end; i++) {
                    this.weeks.push(i);
                }
            },

            //get
            getWeekDayInSemester(numberDay) {
                let pn = [];

                let start = getWeekNumber(new Date(this.startSemester));
                let end = getWeekNumber(new Date(this.finishSemester));

                let date;

                for (let i = start; i <= end; i++) {
                    date = getDayInWeek(new Date().getFullYear(), i, numberDay);
                    pn.push(new Date(date).getTime());
                }

                return pn;
            },
            getLessonsByDate(date) {
                let lessons = [];
                this.schedules.forEach(schedule => {
                    if (dateLongToString(schedule.lessonDate) === dateLongToString(date)) {
                        lessons.push(schedule);
                    }
                });

                if (lessons.length > 0) {
                    for (let i = 1; i <= this.maxLessonsInDay; i++) {
                        let lessonExistence = false;
                        lessons.forEach(lesson => {
                            if (lesson.lessonNumber === i) {
                                lessonExistence = true;
                            }
                        });

                        if (lessonExistence) {
                            lessonExistence = false;
                        } else {
                            let lesson = {
                                lessonNumber: i,
                                lessonDate: dateLongToStringForInput(date),
                                note: "empty"
                            };
                            lessons.push(lesson);
                        }
                    }

                    lessons.sort((a, b) => a.lessonNumber > b.lessonNumber ? 1 : -1);
                }
                return lessons;
            },
            //choose
            chooseLesson(name) {
                this.newLessonName = name;
            },
            chooseLessonType(name) {
                this.newLessonType = name;
            },
            chooseTeacher(name) {
                this.newTeacherSurname = name;
            },

            //date
            dateToString(date) {
                return dateLongToString(date);
            },

            //toggle
            toggleMode() {
                this.mode = !this.mode;
            },
            toggleDay(date) {
                this.newSchedule.lessonDate = dateLongToStringForInput(date)
            },
            toggleLesson(date, number) {
                this.newSchedule.lessonDate = dateLongToStringForInput(date);
                this.newSchedule.lessonNumber = number;
            },
        }
    }
</script>
<style scoped>
    .wrapper {
        display: flex;
        flex-direction: column;
        justify-content: center;
        margin-top: 1%;
        margin-bottom: 70vh;
    }

    .panel-schedule-form {
        position: fixed; /* Фиксированное положение */
        left: 0;
        bottom: 0; /* Левый нижний угол */
    }


    .schedules {
        margin-left: 20px;
        margin-right: 20px;
    }

    .search {
        position: absolute;
        background-color: lightblue;
        z-index: 999;
    }

    .schedule {
        display: flex;
        flex-direction: row;
        width: 100%;
        /*max-width: 1600px;*/
        overflow-y: auto;
        padding: 15px;
        border: 1px solid black;
        border-radius: 8px;
    }

    .schedule::-webkit-scrollbar {
        width: 10px;
        height: 7px;
    }

    .schedule::-webkit-scrollbar-track {
        background: white;
        border-radius: 5px;
    }

    .schedule::-webkit-scrollbar-thumb {
        background: #888888;
        border-radius: 5px;
    }

    .schedule::-webkit-scrollbar-thumb {
        background: #555555;
    }

    .week {
        display: flex;
        flex-direction: row;
        justify-content: center;
    }

    .days {
        vertical-align: top;
        min-width: 120px;
        max-width: 120px;
    }

    .day {
        display: flex;
        flex-direction: column;
        margin-top: 30px;
        margin-left: 10px;
        margin-right: 10px;
        /*border: 1px solid black;*/
    }

    .day-header {
        display: flex;
        flex-direction: row;
        justify-content: center;
    }

    .day-header {
        display: flex;
        flex-direction: row;
        justify-content: center;
        margin-bottom: 20px;
    }

    .day-footer {
        display: flex;
        flex-direction: row;
        justify-content: center;
    }

    .day-footer span {
        margin-top: 10px;
        margin-bottom: 5px;
    }

    .day-footer span:hover {
        color: LimeGreen;
    }

    /*lesson*/
    .lesson-empty {
        display: flex;
        align-items: center;
        justify-content: center;
        min-width: 100px;
        max-width: 100px;
        min-height: 100px;
        max-height: 100px;
        border-radius: 8px;
        margin: 5px;
        background-color: #ffc34d;
    }

    .lesson-empty:hover {
        background-color: coral;
    }

    .lesson-content:hover {
        background-color: cornflowerblue;
    }

    .lesson-empty span {
        align-items: center;

    }

    .lesson-content {
        display: grid;
        grid-template-columns: 80% 20%;
        min-width: 100px;
        max-width: 100px;
        min-height: 100px;
        max-height: 100px;
        border: 1px solid black;
        border-radius: 20px;
        margin: 5px;
        background-color: #ffc34d;
    }

    .lesson-item {
        display: flex;
        flex-direction: column;
    }

    .lesson-body {
        display: flex;
        flex-direction: column;
        /*justify-content: space-around;*/
    }

    .lesson-btn {
        display: flex;
        flex-direction: column;
        padding: 2px;
        justify-content: space-between;
        border-left: 1px solid black;
    }

    .schedule-form {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }
</style>


<!--let d = date.getDate();-->
<!--let m = date.getMonth() + 1;-->
<!--let y = date.getFullYear();-->

<!--this.newLesson.date = "" + y + "-" + (m <= 9 ? '0' + m : m) + "-" + (d <= 9 ? '0' + d : d);-->