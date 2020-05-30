<template>
    <header class="header-page">
        <div class="container">
            <div class="header-page__inner" v-if="!user">
                <div>
                    <div class="brand">
                        <img class="logo-profile img-circle" src="/images/ic_navbar.png" alt="icon-navbar" width="50" height="50">
                    </div>
                    <ul class="menu">
                        <li><a href="#" class="menu-link">
                            Новини
                        </a></li>
                        <li><a href="#" class="menu-link">
                            Контакти
                        </a></li>
                        <li><a href="#" class="menu-link">
                            Про нас
                        </a></li>

                    </ul>
                </div>
                <div>
                    <a href="/login" class="sign-link">
                        Вхід
                    </a>
                    <a href="/registration" class="sign-link">
                        Реєстрація
                    </a>
                </div>
            </div>
            <div class="header-page__inner" v-else>
                <div>
                    <div class="brand">
                        <img class="logo-profile img-circle" src="images/ic_navbar.png" alt="icon-navbar" width="50" height="50">
                    </div>
                    <ul class="menu">
                        <li><router-link to="/" class="menu-link">
                            Головна
                        </router-link></li>
                        <li><router-link to="/profile" class="menu-link">
                            {{user}}
                        </router-link></li>
                    </ul>
                </div>
                <div>
                    <a href="/logout" class="sign-link">
                        Вихід
                    </a>
                </div>
            </div>
        </div>
    </header>
</template>
<script>
    export default {
        props: ["user"]
    }
</script>
<style scoped>
    /* --- header page --- */
    .header-page {
        width: 100%;
        padding: 15px 0;
        background-color: var(--mainDark);
        color: var(--mainWhite);
    }
    .header-page__inner,
    .header-page__inner>div {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }
    .header-page__inner a {
        color: var(--mainWhite);
    }
    .menu-link {
        transition: var(--transDef);
    }
    .menu-link:hover {
        color: #CD6BCF;
    }
    .brand {
        cursor: pointer;
        font-size: 32px;
        margin-right: 15px;
    }
    .menu {
        display: flex;
        flex-direction: row;
        justify-content: start;
        margin-top: 10px;
    }
    .menu li {
        list-style: none;
        margin-right: 15px;
        line-height: 2;
    }
    .sign-link {
        margin-left: 5px;
        line-height: 2;
        padding: 0 15px;
        margin-top: 10px;
        margin-bottom: 10px;
        border: 1px solid transparent;
        border-radius: 4px;
        transition: var(--transDef);
    }
    .sign-link:hover {
        color: var(--mainWhite);
        border: 1px solid var(--mainWhite);
    }
</style>