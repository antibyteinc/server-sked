<template>
    <div class="wrapper">
        <header-block :user="user"></header-block>
        <div class="content">
            <institution v-if="user"></institution>
            <router-view></router-view>
        </div>
        <div class="footer"></div>
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