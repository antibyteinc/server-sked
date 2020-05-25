<template>
    <div class="wrapper__group">
        <div class="groups" v-if="groups.length > 0">
            <div class="group" v-for="group in groups">
                <router-link :to="'/view-schedules/' + group.id"><button>{{group.name}}</button></router-link>
            </div>
        </div>
        <div class="groups" v-else>
            <p>Список груп порожній</p>
        </div>
    </div>
</template>
<script>
    export default {
        props: ["department"],
        data() {
            return {
                groupApi: this.$resource("/group{/id}"),
                groups: [],
            }
        },
        created() {
            this.getGroupFromDb()
        },
        methods: {
            getGroupFromDb() {
                this.groups = [];
                this.groupApi.get({id: this.department.id}).then(res => {
                    res.json().then(data => {
                        data.forEach(group => {
                            this.groups.push(group);
                        })
                    })
                })
            }
        }
    }
</script>
<style>
    .group {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }
</style>