<template>
    <div class="wrapper__profile">
        <div class="profile">
            <router-link to="/my-institutions"><button>Розклад</button></router-link>
            <router-link to="/my-institution-page" ><button>Сторінка закладу</button></router-link>
            <router-link to="/my-teachers/"><button>Викладачі</button></router-link>
            <router-link to="/my-lessons"><button>Предмети</button></router-link>
            <router-link to="/my-classrooms/"><button>Аудиторії</button></router-link>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {

            }
        },
    }
</script>
<style scoped>

</style>