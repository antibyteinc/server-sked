<template>
    <footer class="footer-page">
        <div class="container">
            <div class="footer-page__inner">
                <div class="left">
					<span class="copyright">
						&copy; 2020 hrankin, Inc.
					</span>
                    <a href="#" class="footer-link">
                        Terms
                    </a>
                    <a href="#" class="footer-link">
                        Privacy
                    </a>
                </div>
                <div class="right">
                    <a href="#" class="footer-link">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a href="#" class="footer-link">
                        <i class="fab fa-facebook-square"></i>
                    </a>
                    <a href="#" class="footer-link">
                        <i class="fab fa-youtube"></i>
                    </a>
                    <a href="#" class="footer-link">
                        <i class="fab fa-linkedin"></i>
                    </a>
                    <a href="#" class="footer-link">
                        <i class="fab fa-github"></i>
                    </a>
                </div>
            </div>
        </div>
    </footer>
</template>
<script>
    export default {

    }
</script>
<style scoped>
    .footer-page {
        width: 100%;
        padding: 30px 0;
        background-color: var(--mainWhite);
        color: var(--mainSilver);
    }
    .footer-page__inner,
    .footer-page__inner>div {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }
    .header-page__inner a {
        color: var(--mainSilver);
    }
    .copyright {
        margin-right: 15px;
    }
    .footer-link {
        margin-left: 15px;
        color: var(--mainSilver);
        transition: var(--transDef);
    }
    .footer-link:hover {
        color: var(--mainDark);
    }
</style>