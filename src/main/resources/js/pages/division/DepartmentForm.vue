<template>
    <div class="department-form">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Назва кафедри" v-model="newDepartment.name" @keyup.enter="addDepartment(course.id)">

            <span class="input-group-btn">
                <button class="btn btn-default" type="button" @click="addDepartment(course.id)">Додати</button>
            </span>
        </div>
    </div>
</template>
<script>
    export default {
        props: ["course", "departments"],
        data() {
            return {
                departmentApi: this.$resource("/department{/id}"),
                newDepartment: {
                    name: "",
                }
            }
        },
        methods: {
            addDepartment(id) {
                this.departmentApi.save({id: id}, this.newDepartment).then(res => {
                    res.json().then(data => {
                        this.departments.push(data)
                    })
                });
                this.newDepartment.name = "";
            },
        }
    }
</script>
<style>
    .department-form {
        margin-top: 20px;
        margin-bottom: 10px;
    }
</style>