<template>
    <div class="wrapper__department">
        <div class="departments" v-if="departments.length > 0">
            <div class="department" v-for="department in departments">
                <div class="department-header">
                    <div class="department-name"><span>каф. </span>{{department.name}}</div>
                </div>

                <div class="department-body">
                    <view-group :department="department"></view-group>
                </div>
            </div>
        </div>
        <div class="departments" v-else>
            <p>Список кафедр порожній</p>
        </div>
    </div>
</template>
<script>
    import ViewGroup from "./ViewGroup.vue";

    export default {
        props: ["course"],
        components: {
            ViewGroup
        },
        created() {
            this.getDepartmentFromDb();
        },
        data() {
            return {
                departmentApi: this.$resource("/department{/id}"),
                departments: [],
            }
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