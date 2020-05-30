<template>
    <div class="wrapper">
        <div class="wrapper__profile panel panel-default col-md-6 col-md-offset-3">
            <div class="panel-body">
                <div class="container_button">
                    <div class="button_table">
                        <router-link to="/lessons" :to="linkDivision">
                            <img class="logo-profile img-circle" src="/images/profile_constructor.png" alt="icon-lesson"
                                 width="100" height="100" @click="goToDivisions">
                            <p class="text-center" @click="goToDivisions">Конструктор розкладів</p>
                        </router-link>
                    </div>
                    <div class="button_table">
                        <router-link to="/my-institute">
                            <img class="logo-profile img-circle" src="/images/profile_page.png" alt="icon-page"
                                 width="100" height="100">
                            <p class="text-center">Інформація закладу</p>
                        </router-link>
                    </div>
                    <div class="button_table">
                        <router-link to="/lessons">
                            <img class="logo-profile img-circle" src="/images/profile_lesson.png" alt="icon-lesson"
                                 width="100" height="100">
                            <p class="text-center">Предмети</p>
                        </router-link>
                    </div>
                    <div class="button_table">
                        <router-link to="/teachers/">
                            <img class="logo-profile img-circle" src="/images/profile_teacher.png" alt="icon-teacher"
                                 width="100" height="100">
                            <p class="text-center">Викладачі</p>
                        </router-link>
                    </div>
                    <div class="button_table">
                        <router-link to="/classrooms/">
                            <img class="logo-profile img-circle" src="/images/profile_building.png" alt="icon-building"
                                 width="100" height="100">
                            <p class="text-center">Аудиторії</p>
                        </router-link>
                    </div>

                </div>
            </div>
            <modal-enter-name-institute v-if="modeModal"
                                        @close="closeModal"
                                        @save="saveInstituteName">
            </modal-enter-name-institute>
        </div>

        <div class="panel-image panel panel-default col-md-6 col-md-offset-3">
            <div class="panel-body">
                <img class="image-people" src="/images/people_work.jpg" alt="icon-lesson">
            </div>
        </div>

        <div class="title-institute">
            <p class="text-center" style="font-size: 25px; margin-top: 15px; margin-bottom: 30px; font-family: var(--fontHand);">Нам важливо щоб у вас завжди був під рукою доступ до необхідних інструментів.</p>
        </div>

    </div>
</template>
<script>
    import ModalEnterNameInstitute from "../components/modal/ModalEnterNameInstitute.vue";

    export default {
        components: {
            ModalEnterNameInstitute
        },
        data() {
            return {
                instituteName: "",
                modeModal: false,
                linkDivision: ""
            }
        },
        created() {
            this.getInstitutionNameFromDb();
        },
        methods: {
            getInstitutionNameFromDb() {
                this.$resource("/institute/get-institute-name").get().then(res => {
                    res.text().then(data => {
                        this.instituteName = data;
                    })
                });
            },
            goToDivisions() {
                this.getInstitutionNameFromDb();
                if (this.instituteName === "") {
                    this.linkDivision = "";
                    this.modeModal = true;
                } else {
                    this.linkDivision = "/divisions";
                }
            },
            closeModal() {
                this.modeModal = false;
            },
            saveInstituteName(data) {
                this.$resource("/institute/add-institute-name").update(data).then(res => {
                    if (res.ok) {
                        this.getInstitutionNameFromDb();
                        this.modeModal = false;
                    }
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
    }

    .wrapper__profile {
        margin-top: 3%;
        /*margin-bottom: 100vh;*/
    }

    .container_button {
        display: flex;
        flex-direction: row;
        margin-top: 20px;
        justify-content: space-between;
    }

    .button_table {
        display: flex;
        flex-direction: column;
        width: 100px;
        margin-left: 20px;
        margin-right: 20px;
    }

    .image-people {
        max-width: 100%;
        display: block;
        margin: 0 auto;
    }

</style>