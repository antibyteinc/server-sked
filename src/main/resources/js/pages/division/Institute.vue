<template>
    <div>
        <label>
            Назва: <input type="text" v-model="institute.name">
        </label>
        <label>
            Описа: <input type="text" v-model="institute.description">
        </label>
        <label>
            Сайт: <input type="text" v-model="institute.site">
        </label>
        <label>
            Пошта: <input type="text" v-model="institute.mail">
        </label>
        <label>
            Телефон: <input type="text" v-model="institute.phone">
        </label>
        <button @click="update">Зберегти</button>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                instituteApi: this.$resource("/institute/get-institute"),
                institute: {
                    name: "",
                    description: "",
                    site: "",
                    mail: "",
                    phone: ""
                },
            }
        },
        created() {
            this.getInstituteFromDb();
        },
        methods: {
            getInstituteFromDb() {
                this.instituteApi.get().then(res => {
                    res.json().then(data => {


                        this.institute.name = data.name;
                        this.institute.description = data.description;
                        this.institute.site = data.site;
                        this.institute.mail = data.mail;
                        this.institute.phone = data.phone;
                    })
                })
            },
            update() {
                this.$resource("/institute/update-institute{/id}").update({id: frontendData.id}, this.institute).then(res => {
                    res.json().then(data => {
                        this.institute.name = data.name;
                        this.institute.description = data.description;
                        this.institute.site = data.site;
                        this.institute.mail = data.mail;
                        this.institute.phone = data.phone;
                    })
                })
            }
        }
    }
</script>
<style>

</style>