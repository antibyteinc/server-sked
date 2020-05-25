<template>
    <div>
        <p>Знайти розклад</p>
        <input type="text" placeholder="Назва закладу" v-model="searchInstitution">
        <div class="search">
            <router-link :to="'/view/' + institution.userId"
                         v-for="institution in inputInstitutions"
                         :key="institution.id">
                {{institution.name}}
            </router-link>
        </div>
        <button>Дивитись</button>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                institutionApi: this.$resource("/institute/get-institutions"),
                institutions: [],

                searchInstitution: "",
                inputInstitutions: []
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