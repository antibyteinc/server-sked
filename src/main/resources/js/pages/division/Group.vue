<template>
    <div class="wrapper__group">
        <div class="groups" v-if="groups.length > 0">
            <div class="group" v-for="group in groups">
                <router-link :to="'/schedules/' + group.id"><button class="btn-group-my btn btn-default">{{group.name}}</button></router-link>
                <span class="glyphicon glyphicon-remove" @click="removeGroup(group.id)"></span>
            </div>
        </div>
        <div class="groups" v-else>
            <p>Список груп порожній</p>
        </div>
        <div class="group-form">
            <div class="input-group">
                <input type="text" class="form-control"
                       placeholder="Назва групи" v-model="newGroup.name" @keyup.enter="addGroup">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" @click="addGroup">Додати</button>
                </span>
            </div>
        </div>
    </div>
</template>
<script>
    function getIndexItemById(list, id) {
        for (let i = 0; list.length; i++) {
            if (list[i].id === id) {
                return i;
            }
        }
        return -1;
    }

    export default {
        props: ["department"],
        data() {
            return {
                groupApi: this.$resource("/group{/id}"),
                groups: [],
                newGroup: {
                    name: ""
                }
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
            },
            addGroup() {
                this.groupApi.save({id: this.department.id}, this.newGroup).then(res => {
                    res.json().then(data => {
                        this.groups.push(data)
                    })
                });
                this.newGroup.name = "";
            },
            removeGroup(id) {
                this.groupApi.delete({id: id}).then(res => {
                    if (res.ok) {
                        let index = getIndexItemById(this.groups, id);
                        this.groups.splice(index, 1);
                    }
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

    .btn-group-my {
        margin-top: 4px;
        margin-bottom: 4px;
        width: 150px;
    }
</style>