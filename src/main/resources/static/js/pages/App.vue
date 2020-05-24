<template>
    <div class="wrapper">
        <header-block :user="user"></header-block>
        <main-page v-if="!user"></main-page>
        <router-view></router-view>
    </div>
</template>
<script>
    import HeaderBlock from "../components/HeaderBlock.vue";
    import MainPage from "./MainPage.vue";

    export default {
        data() {
            return {
                user: frontendData.username
            }
        },
        components: {
            HeaderBlock, MainPage
        }
    }
</script>
<style>

</style>