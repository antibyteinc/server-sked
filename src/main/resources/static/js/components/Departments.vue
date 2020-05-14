<template>
    <div class="wrapper__department">
        <div>
            <department-form :course="course" :departments="departments"></department-form>
        </div>
        <div class="departments" v-if="departments.length > 0">
            <div class="department" v-for="department in departments">
                <div class="department-header">
                    <div class="department-name"><span>каф. </span>{{department.name}}</div>
                    <span class="glyphicon glyphicon-remove" @click="removeDepartment(department.id)"></span>
                </div>

                <div class="department-body">
                    <groups :department="department"></groups>
                </div>
            </div>
        </div>
        <div class="departments" v-else>
            <p>Список кафедр порожній</p>
        </div>
    </div>
</template>
<script>
    import DepartmentForm from "./DepartmentForm.vue";
    import Groups from "./Groups.vue";

    function getIndexItemById(list, id) {
        for (let i = 0; list.length; i++) {
            if (list[i].id === id) {
                return i;
            }
        }
        return -1;
    }

    export default {
        props: ["course"],
        data() {
            return {
                departmentApi: this.$resource("/departments{/id}"),
                departments: [],
            }
        },
        watch: {},
        created() {
            this.getDepartmentFromDb();
        },
        components: {
            DepartmentForm, Groups
        },
        methods: {
            getDepartmentFromDb() {
                this.departments = [];
                this.departmentApi.get({id: this.course.id}).then(res => {
                    res.json().then(data => {
                        data.forEach(department => {
                            this.departments.push(department);
                        })
                    })
                });
                return this.departments;
            },
            removeDepartment(id) {
                this.departmentApi.delete({id: id}).then(res => {
                    if (res.ok) {
                        let index = getIndexItemById(this.departments, id);
                        this.departments.splice(index, 1);
                    }
                })
            }
        }
    }
</script>
<style>
    .department {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        padding: 10px;
        border: 1px solid black;
        border-radius: 5px;
        margin-bottom: 10px;
    }

    .department-form {
        margin-top: 20px;
        margin-bottom: 10px;
    }

    .department-header {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        border-bottom: 1px solid black;
        margin-bottom: 15px;
    }
</style>