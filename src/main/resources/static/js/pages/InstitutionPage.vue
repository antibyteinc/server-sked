<template>
    <div>
        <div>
            <button @click="toggleEditMode" :disabled="!editMode">Перегляд</button>
            <button @click="toggleEditMode" :disabled="editMode">Редагування</button>
        </div>
        <div v-if="!editMode">
            <p>{{institutionPage.name}}</p>
            <p>{{institutionPage.description}}</p>
            <p>{{institutionPage.site}}</p>
            <p>{{institutionPage.phone}}</p>
            <p>{{institutionPage.mail}}</p>
        </div>
        <div v-else>
            <input type="text" placeholder="Назва закладу" v-model="institutionPage.name">
            <textarea name="" id="" cols="30" rows="10" placeholder="Опис"
                      v-model="institutionPage.description"></textarea>
            <input type="text" placeholder="Веб-сайт" v-model="institutionPage.site">
            <input type="text" placeholder="Контактний телефон" v-model="institutionPage.phone">
            <input type="text" placeholder="Пошта" v-model="institutionPage.mail">
            <button @click="update">Зберегти</button>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                editMode: true,
                userId: frontendData.id,
                institutionPageApi: this.$resource("/institution-page/edit{/id}"),
                institutionPage: {}
            }
        },
        created() {
            this.getInstitutionPageFromDb();
        },
        methods: {
            getInstitutionPageFromDb() {
                this.institutionPageApi.get().then(res => {
                    res.json().then(data => {
                        this.institutionPage = data
                    })
                });
            },
            update() {
                let page = {
                    name: this.institutionPage.name,
                    description: this.institutionPage.description,
                    site: this.institutionPage.site,
                    phone: this.institutionPage.phone,
                    mail: this.institutionPage.mail
                };

                this.institutionPageApi.update({id: this.institutionPage.id}, page).then(res => {
                    res.json().then(data => {
                        this.institutionPage = data;
                    })
                });

                this.editMode = !this.editMode;
            },
            toggleEditMode() {
                this.editMode = !this.editMode;
            }
        }

    }
</script>
<style>

</style>