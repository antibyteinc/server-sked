<template>
    <div class="wrapper">
        <header-block :user="user"></header-block>
        <router-link to="/my-institutions">Мої інститути</router-link>
        <router-view></router-view>
    </div>
</template>
<script>
    import HeaderBlock from "../components/HeaderBlock.vue";
    import Institution from "../components/Institutions.vue";

    export default {
        data() {
            return {
                user: frontendData.username
            }
        },
        components: {
            HeaderBlock, Institution
        }
    }
</script>
<style>

</style>