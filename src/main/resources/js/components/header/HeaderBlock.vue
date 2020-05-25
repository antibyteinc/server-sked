<template>
    <div class="header">
        <div class="header-content" v-if="!user">
            <div>
                <router-link to="/main">Головна</router-link>
                <a href="/login"><button>Вхід</button></a>
                <a href="/registration"><button>Реєстрація</button></a>
            </div>
        </div>
        <div class="header-content" v-else>
            <router-link to="/main">Головна</router-link>
            <router-link to="/profile">{{user}}</router-link>
            <a href="/logout"><button>Вихід</button></a>
        </div>
    </div>
</template>
<script>
    export default {
        props: ["user"]
    }
</script>
<style scoped>
    .header {
        background: lightblue;
    }

    .header-content {
        display: flex;
        flex-direction: row;
        flex-wrap: nowrap;
        justify-content: space-around;
    }
</style>