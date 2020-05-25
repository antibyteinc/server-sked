<template>
    <div class="wrapper__view">
        <div class="view">
            <div class="information">
                <router-link :to="'/view-institute/' + userId">Інформація</router-link>
            </div>
            <div class="schedule">
                <router-link :to="'/view-division/' + userId">Розклад</router-link>
            </div>
            <div class="teacher">
                <router-link :to="'/view-teacher/' + userId">Викладачі</router-link>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        props: ["userId"]
    }
</script>
<style scoped>
    .wrapper__view {

    }
    .view {
        display: flex;
        flex-direction: row;
    }
    .information {
        width: 200px;
        height: 200px;
        border: 1px solid black;
    }
    .schedule {
        width: 200px;
        height: 200px;
        border: 1px solid black;
        margin-left: 20px;
        margin-right: 20px;
    }
    .teacher {
        width: 200px;
        height: 200px;
        border: 1px solid black;
    }

    /*hover*/
    .information:hover, .schedule:hover, .teacher:hover {
        background-color: aqua;
    }
</style>