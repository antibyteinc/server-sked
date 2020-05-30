<template>
    <div>
        <section class="section start-section" :style="{ 'background-image' : 'url(images/background_start.jpg)' }">
            <div class="start-section__inner">
                <h2 class="title start">
                    Знайти розклад
                </h2>
                <form class="start-search">
                    <input type="text" class="search-input">
                    <button class="search-btn">
                        <i class="fas fa-search"></i>
                    </button>
                </form>
            </div>
        </section>

        <section class="section" :style="{ 'background-image' : 'url(images/background_mobile.jpg)' }">
            <div class="container">
                <div class="section__inner">
                    <div class="left">
                        <img src="images/slider_mobile.gif" alt="phone" class="image phone">
                    </div>
                    <div class="right">
                        <h2 class="title">
                            Завантажити додаток.
                            Розклад твого закладу буде завжди з тобою
                        </h2>
                        <a href="#" class="button">
                            Завантажити
                        </a>
                    </div>
                </div>
            </div>
        </section>

        <section class="section" :style="{ 'background-image' : 'url(images/background_workplace.jpg)' }">
            <div class="container">
                <div class="section__inner">
                    <div class="left constructor">
                        <h2 class="title_constructor">
                            Безкоштовний конструктор розкладів для закладу
                        </h2>
                        <a href="/login" class="button_constructor" v-if="!user">
                            Почати
                        </a>
                        <a href="/profile" class="button_constructor" v-else>
                            Почати
                        </a>
                    </div>
                    <div class="right">
                        <!--                        <img src="img/slider.png" alt="slider" class="image">-->
                    </div>
                </div>
            </div>
        </section>
    </div>


    <!--    <div>-->
    <!--        <p>Знайти розклад</p>-->
    <!--        <input type="text" placeholder="Назва закладу" v-model="searchInstitution">-->
    <!--        <div class="search">-->
    <!--            <router-link :to="'/view/' + institution.userId"-->
    <!--                         v-for="institution in inputInstitutions"-->
    <!--                         :key="institution.id">-->
    <!--                {{institution.name}}-->
    <!--            </router-link>-->
    <!--        </div>-->
    <!--        <button>Дивитись</button>-->
    <!--    </div>-->
</template>
<script>
    export default {
        data() {
            return {
                institutionApi: this.$resource("/institute/get-institutions"),
                institutions: [],

                searchInstitution: "",
                inputInstitutions: [],
                user: frontendData.username
            }
        },
        created() {
            this.getInstitutionsFromDb();
        },
        watch: {
            searchInstitution() {
                this.inputInstitutions = [];
                if (this.searchInstitution !== "") {
                    this.institutions.forEach(institution => {
                        if (institution.name.toLowerCase().indexOf(this.searchInstitution.toLowerCase()) > -1) {
                            if (institution.name === this.searchInstitution) {
                                this.inputInstitutions = []
                            } else {
                                let inst = {
                                    id: institution.id,
                                    name: institution.name,
                                    userId: institution.user.id
                                };
                                this.inputInstitutions.push(inst);
                            }
                        }
                    })
                }
            }
        },
        methods: {
            getInstitutionsFromDb() {
                this.institutionApi.get().then(res => {
                    res.json().then(data => {
                        data.forEach(institution => {
                            this.institutions.push(institution)
                        })
                    })
                })
            }
        }
    }
</script>
<style>

</style>