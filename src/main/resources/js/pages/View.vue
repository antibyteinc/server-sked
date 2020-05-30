<template>
    <div>
        <div class="wrapper__view">
            <div class="view">
                <div class="button_table">
                    <router-link :to="'/view-institute/' + userId">
                        <img class="logo-profile img-circle" src="/images/profile_page.png" alt="icon-lesson"
                             width="100" height="100">
                        <p class="text-center">Інформація</p>
                    </router-link>
                </div>
                <div class="button_table">
                    <router-link :to="'/view-division/' + userId">
                        <img class="logo-profile img-circle" src="/images/profile_constructor.png" alt="icon-lesson"
                             width="100" height="100">
                        <p class="text-center">Розклад</p>
                    </router-link>
                </div>
                <div class="button_table">
                    <router-link :to="'/view-teacher/' + userId">
                        <img class="logo-profile img-circle" src="/images/profile_teacher.png" alt="icon-lesson"
                             width="100" height="100">
                        <p class="text-center">Викладачі</p>
                    </router-link>
                </div>
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
        width: 100%;
        align-items: center;
        justify-content: center;
        margin-top: 20px;
        margin-bottom: 20px;
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

    .button_table {
        display: flex;
        flex-direction: column;
        width: 100px;
        margin-left: 20px;
        margin-right: 20px;
    }
</style>