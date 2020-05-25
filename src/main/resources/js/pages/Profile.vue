<template>
    <div class="wrapper__profile">
        <div class="profile">
            <router-link :to="linkDivision"><button @click="goToDivisions">Розклад</button></router-link>
            <router-link to="/my-institute">Мій інститут</router-link>
            <router-link to="/teachers/"><button>Викладачі</button></router-link>
            <router-link to="/lessons"><button>Предмети</button></router-link>
            <router-link to="/classrooms/"><button>Аудиторії</button></router-link>
        </div>

        <modal-enter-name-institute v-if="modeModal"
                                    @close="closeModal"
                                    @save="saveInstituteName">
        </modal-enter-name-institute>
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

</style>