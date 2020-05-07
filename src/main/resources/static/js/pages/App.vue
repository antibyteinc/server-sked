<template>
    <div class="wrapper">
        <div class="header">
            <div v-if="!user">
                <a href="/login"><button>Вхід</button></a>
                <a href="/registration"><button>Реєстрація</button></a>
            </div>
            <div v-else>
                <p>{{user}}</p>
                <a href="/logout"><button>Вихід</button></a>
            </div>
        </div>
        <div class="content"></div>
        <div class="footer"></div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                user: frontendData.username
            }
        }
    }
</script>
<style>

</style>