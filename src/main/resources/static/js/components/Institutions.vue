<template>
    <div class="wrapper">
        <div class="wrapper__institution panel panel-default col-md-8 col-md-offset-2">
            <h3>Мої інститути</h3>
            <div class="institutions">
                <div v-if="institutions.length > 0">
                    <table class="institution">
                        <tr>
                            <th>id</th>
                            <th>Назва</th>
                            <th>Дата ред.</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <tr v-for="institution in institutions" :key="institution.id">
                            <td>{{institution.id}}</td>
<!--                            <td><router-link :to="'/my-semesters/' + institution.id + '/' + institution.name"><button>{{institution.name}}</button></router-link></td>-->
                            <td><button @click="switchInstitution(institution)">{{institution.name}}</button></td>
                            <td>{{institution.lastModified}}</td>
                            <td><span class="glyphicon glyphicon-remove" @click="removeInstitution(institution.id)"></span></td>
                        </tr>
                    </table>
                </div>
                <div v-else>
                    <p>Список інститутів порожній</p>
                </div>
            </div>
            <div class="institution-panel">
                <input type="text" placeholder="Назва інституту" v-model="newInstitution.name">
                <button @click="addInstitution">Додати</button>
            </div>
        </div>
        <component v-if="currentInstitution" is="semesters" :institution="currentInstitution" @closeSemester="closeSemester"></component>
    </div>
</template>
<script>
    import Semesters from "./Semesters.vue";

    function getIndexItemById(list, id) {
        for (let i = 0; list.length; i++) {
            if (list[i].id === id) {
                return i;
            }
        }
        return -1;
    }

    export default {
        data() {
            return {
                currentInstitution: null,

                institutionApi: this.$resource("/institutions/user{/id}"),
                institutions: [],
                newInstitution: {
                    name: ""
                }
            }
        },
        components: {
            Semesters
        },
        created() {
            this.getInstitutionsFromDb()
        },
        methods: {
            closeSemester(data) {
                if (data) {
                    this.currentInstitution = null;
                }
            },
            switchInstitution(institution) {
                this.currentInstitution = institution;
            },
            getInstitutionsFromDb() {
                this.institutions = [];
                this.institutionApi.get().then(res => {
                    res.json().then(data => {
                        data.forEach(institution => {
                            this.institutions.push(institution);
                        })
                    })
                })
            },
            addInstitution() {
                this.institutionApi.save(this.newInstitution).then(res => {
                    res.json().then(data => {
                        this.institutions.push(data);

                        this.newInstitution = {
                            name: ""
                        }
                    })
                })
            },
            removeInstitution(id) {
                this.institutionApi.delete({id: id}).then(res => {
                    if (res.ok) {
                        let index = getIndexItemById(this.institutions, id);
                        this.institutions.splice(index, 1);
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

    .wrapper__institution {
        margin-top: 50px;
        padding: 10px;
    }

    .institution {
        width: 50%;
    }
</style>