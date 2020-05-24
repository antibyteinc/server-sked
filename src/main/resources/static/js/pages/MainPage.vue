<template>
    <div>
        <p>Знайти розклад</p>
        <input type="text" placeholder="Назва закладу" v-model="inputInstitution">
        <div class="search">
            <a v-for="institution in inputInstitutions">{{institution.name}}</a>
        </div>
        <button>Дивитись</button>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                institutionApi: this.$resource("/institutions"),
                institutions: [],

                inputInstitution: "",
                inputInstitutions: []
            }
        },
        created() {
            this.getInstitutionsFromDb();
        },
        watch: {
            inputInstitution() {
                this.inputInstitutions = [];
                if (this.inputInstitution !== "") {
                    this.institutions.forEach(institution => {
                        if (institution.name.toLowerCase().indexOf(this.inputInstitution.toLowerCase()) > -1) {
                            if (institution.name === this.inputInstitution) {
                                this.inputInstitutions = []
                            } else {
                                let inst = {
                                    id: institution.id,
                                    name: institution.name
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