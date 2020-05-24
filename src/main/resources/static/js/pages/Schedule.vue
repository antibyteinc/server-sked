<template>
    <div class="wrapper__schedule">
        <div>
            <h3>Розклад групи {{groupName}}</h3>
            <h4>Період з {{dateToString(startSemester)}} по {{dateToString(finishSemester)}}</h4>
        </div>
        <div class="schedule-mode">
            <button :disabled="!mode" @click="toggleMode">Таблица</button>
            <button :disabled="mode" @click="toggleMode">Плитка</button>

            <div>
                <label>
                    макс. предметів в день <input type="text" placeholder="макс. предметів в день" v-model="maxLessonsInDay">
                </label>
            </div>
        </div>
        <div class="schedule-form">
            <select v-model="newSchedule.lessonNumber">
                <option :value="num" v-for="num in maxLessonsInDay">{{num}}</option>
            </select>
            <div>
                <input type="text" placeholder="Назва предмету" v-model="newLessonName">
                <div class="search">
                    <p v-for="name in lessonNames" @click="chooseLesson(name)">{{name}}</p>
                </div>
            </div>
            <div>
                <input type="text" placeholder="Назва типу предмета" v-model="newLessonType">
                <div class="search">
                    <p v-for="name in lessonTypeNames" @click="chooseLessonType(name)">{{name}}</p>
                </div>
            </div>
            <div>
                <input type="text" placeholder="Призвище викладача" v-model="newTeacherSurname">
                <div class="search">
                    <p v-for="surname in surnamesTeachers" @click="chooseTeacher(surname)">{{surname}}</p>
                </div>
            </div>

            <input type="text" placeholder="Корпус" v-model="newSchedule.building">
            <input type="text" placeholder="Аудиторія" v-model="newSchedule.cabinet">
            <input type="date" min="" max="" placeholder="Дата" v-model="newSchedule.lessonDate">
            <input type="text" placeholder="Примітка" v-model="newSchedule.note">
            <button @click="addSchedule">Додати</button>
        </div>
        <div class="schedules" v-if="!mode">
            <div class="schedules" v-if="schedules.length > 0">
                <div class="schedule">
                    <table>
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
                            <td><span class="glyphicon glyphicon-remove" @click="removeSchedule(schedule.id)"></span>
                            </td>
                        </tr>
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
                            <div class="week">{{week}}</div>
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
                                                            {{lesson.lessonNumber}}
                                                            {{lesson.lessonName}}
                                                            {{lesson.lessonType}}
                                                            {{lesson.teacherSurname}}
                                                            {{lesson.building}}
                                                            {{lesson.cabinet}}
                                                            {{lesson.note}}
                                                        </div>
                                                    </div>
                                                    <div class="lesson-btn">
                                                    <span class="glyphicon glyphicon-remove"
                                                          @click="removeSchedule(lesson.id)"></span>
                                                    </div>
                                                </div>
                                                <div class="lesson-empty" v-else @click="toggleLesson(lesson.lessonDate, lesson.lessonNumber)">
                                                    <span class="glyphicon glyphicon-plus"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="day-footer">
                                    <span class="day-btn-add glyphicon glyphicon-plus" @click="toggleDay(day)"></span>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
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
        watch: {
            institutionId() {
                this.getLessonsFromDb();
                this.getLessonsTypeFromDb();
                this.getTeachersFromDb();
            },
            newLessonName() {
                this.lessonNames = [];
                if (this.newLessonName !== "") {
                    this.lessons.forEach(lesson => {
                        if (lesson.name.toLowerCase().indexOf(this.newLessonName.toLowerCase()) > -1) {
                            if (lesson.name === this.newLessonName) {
                                this.lessonNames = [];
                            } else {
                                this.lessonNames.push(lesson.name)
                            }
                        }
                    })
                }
            },
            newLessonType() {
                this.lessonTypeNames = [];
                if (this.newLessonType !== "") {
                    this.lessonsType.forEach(type => {
                        if (type.name.toLowerCase().indexOf(this.newLessonType.toLowerCase()) > -1) {
                            if (type.name === this.newLessonType) {
                                this.lessonTypeNames = [];
                            } else {
                                this.lessonTypeNames.push(type.name)
                            }
                        }
                    })
                }
            },
            newTeacherSurname() {
                this.surnamesTeachers = [];
                if (this.newTeacherSurname !== "") {
                    this.teachers.forEach(teacher => {
                        if (teacher.surname.toLowerCase().indexOf(this.newTeacherSurname.toLowerCase()) > -1) {
                            if (teacher.surname === this.newTeacherSurname) {
                                this.surnamesTeachers = [];
                            } else {
                                this.surnamesTeachers.push(teacher.surname)
                            }
                        }
                    })
                }
            },
        },
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
                scheduleApi: this.$resource("/schedules{/id}"),
                schedules: [],
                newSchedule: {
                    lessonNumber: 1,
                    lessonName: "",
                    lessonType: "",
                    teacherSurname: "",
                    building: "",
                    cabinet: "",
                    lessonDate: dateLongToStringForInput(new Date().getTime()),
                    note: ""
                },

                //lists
                lessons: [],
                lessonNames: [],
                newLessonName: "",

                teachers: [],
                surnamesTeachers: [],
                newTeacherSurname: "",

                lessonsType: [],
                lessonTypeNames: [],
                newLessonType: "",


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
            getLessonsFromDb() {
                this.lessons = [];
                this.$resource("/lessons{/id}").get().then(res => {
                    res.json().then(data => {
                        data.forEach(lesson => {
                            this.lessons.push(lesson);
                        })
                    })
                })
            },
            getLessonsTypeFromDb() {
                this.lessonsType = [];
                this.$resource("/type-lessons{/id}").get().then(res => {
                    res.json().then(data => {
                        data.forEach(type => {
                            this.lessonsType.push(type);
                        })
                    })
                })
            },
            getTeachersFromDb() {
                this.lessons = [];
                this.$resource("/teachers{/id}").get().then(res => {
                    res.json().then(data => {
                        data.forEach(teacher => {
                            this.teachers.push(teacher);
                        })
                    })
                })
            },
            getInstitutionIdByGroupIdFromDb() {
                this.$resource("/institutions/get-id{/id}").get({id: this.groupId}).then(res => {
                    res.text().then(data => {
                        this.institutionId = data.valueOf();
                    });
                });
            },
            getGroupNameByGroupIdFromDb() {
                this.$resource("/groups/get-name{/id}").get({id: this.groupId}).then(res => {
                    res.text().then(data => {
                        this.groupName = data;
                    });
                });
            },
            getSemesterRangeFromDb() {
                this.$resource("/groups/get-semesterRange{/id}").get({id: this.groupId}).then(res => {
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
            //edit schedule
            addSchedule() {
                this.newSchedule.lessonName = this.newLessonName;
                this.newSchedule.lessonType = this.newLessonType;
                this.newSchedule.teacherSurname = this.newTeacherSurname;
                this.newSchedule.lessonDate = new Date(this.newSchedule.lessonDate).getTime();

                this.scheduleApi.save({id: this.groupId}, this.newSchedule).then(res => {
                    res.json().then(data => {
                        this.schedules.push(data);
                        this.newSchedule.lessonDate = dateLongToStringForInput(data.lessonDate);
                    })
                });

                this.newLessonName = "";
                this.newLessonType = "";
                this.newTeacherSurname = "";

                this.newSchedule.lessonName = "";
                this.newSchedule.lessonType = "";
                this.newSchedule.teacherSurname = "";
                this.newSchedule.building = "";
                this.newSchedule.cabinet = "";
                this.newSchedule.note = "";
            },
            removeSchedule(id) {
                this.scheduleApi.delete({id: id}).then(res => {
                    if (res.ok) {
                        let index = getIndexItemById(this.schedules, id);
                        this.schedules.splice(index, 1);
                    }
                })
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
    .wrapper__schedule {
        margin: 0 auto;
        width: 100%;
        /*max-width: 1600px;*/
        position: absolute;
        z-index: 100;
    }

    .schedule-form {
        display: flex;
        flex-direction: row;
        flex-wrap: nowrap;
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
        /*display: grid;*/
        /*grid-template-columns: 80% 20%;*/
        display: flex;
        align-items: center;
        justify-content: center;
        min-width: 100px;
        max-width: 100px;
        min-height: 100px;
        max-height: 100px;
        /*border: 1px solid black;*/
        border-radius: 8px;
        background-color: white;
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
        border-radius: 8px;
        background-color: white;
    }

    .lesson-item {
        display: flex;
        flex-direction: column;
    }

    .lesson-body {
        display: flex;
        flex-direction: column;
        justify-content: space-around;
    }

    .lesson-btn {
        display: flex;
        flex-direction: column;
        padding: 2px;
        justify-content: space-between;
        border-left: 1px solid black;
    }
</style>


<!--let d = date.getDate();-->
<!--let m = date.getMonth() + 1;-->
<!--let y = date.getFullYear();-->

<!--this.newLesson.date = "" + y + "-" + (m <= 9 ? '0' + m : m) + "-" + (d <= 9 ? '0' + d : d);-->