package managers;

import pages.DepositPage;
import pages.StartPage;

public class PageManager {

        /**
         * Менеджер страничек
         */
        private static PageManager pageManager;

        /**
         * Стартовая страница
         */
        StartPage startPage;

        /**
         * Страница по вкладам
         */
        DepositPage depositpage;

        /**
         * Конструктор специально запривейтили (синглтон)
         * @see PageManager#getPageManager()
         */
        private PageManager() {
        }

        /**
         * Ленивая инициализация ManagerPages
         *
         * @return ManagerPages
         */
        public static PageManager getPageManager() {
            if (pageManager == null) {
                pageManager = new PageManager();
            }
            return pageManager;
        }

        /**
         * Ленивая инициализация {@link StartPage}
         *
         * @return StartPage
         */
        public StartPage getStartPage() {
            if (startPage == null) {
                startPage = new StartPage();
            }
            return startPage;
        }

        /**
         * Ленивая инициализация {@link DepositPage}
         *
         * @return DepositPage
         */
        public DepositPage getDepositPage() {
            if (depositpage == null) {
                depositpage = new DepositPage();
            }
            return depositpage;
        }

    }
