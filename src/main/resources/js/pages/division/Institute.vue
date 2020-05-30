<template>
    <div class="wrapper">
        <div class="panel-building-form panel panel-default col-md-6 col-md-offset-3">
            <div class="panel-body">
                <label>
                    Назва: <input class="form-control" type="text" v-model="institute.name">
                </label>
                <label>
                    Опис: <input class="form-control" type="text" v-model="institute.description">
                </label>
                <label>
                    Сайт: <input class="form-control" type="text" v-model="institute.site">
                </label>
                <label>
                    Пошта: <input class="form-control" type="text" v-model="institute.mail">
                </label>
                <label>
                    Телефон: <input class="form-control" type="text" v-model="institute.phone">
                </label>
                <button @click="update" class="btn btn-default" style="margin-top: 20px">Зберегти</button>
            </div>
        </div>

        <div class="title-institute">
            <p class="text-center" style="font-size: 25px">Додайте найнеобхіднішу інформацію, щоб люди завжди знали, як можуть знайти вас.</p>
        </div>

        <div class="panel-image panel panel-default col-md-6 col-md-offset-3">
            <div class="panel-body">
                <img class="image-people" src="/images/people_work2.png" alt="icon-lesson">
            </div>
        </div>

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
<style scoped>
    .wrapper {
        display: flex;
        flex-direction: column;
        justify-content: center;
        margin-top: 1%;
    }

    .panel-body {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .panel-body label {
        margin-top: 20px;
    }

    .image-people {
        max-width: 100%;
        display: block;
        margin: 0 auto;
    }

    .title-institute {
        font-family: var(--fontHand);
        margin-bottom: 30px;
        color: black;
    }

</style>