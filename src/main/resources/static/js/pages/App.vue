<template>
    <div class="wrapper">
        <header-block :user="user"></header-block>
        <router-view></router-view>
    </div>
</template>
<script>
    import HeaderBlock from "../components/header/HeaderBlock.vue";
    import MainPage from "./Main.vue";

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