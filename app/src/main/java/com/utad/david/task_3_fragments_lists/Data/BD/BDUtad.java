package com.utad.david.task_3_fragments_lists.Data.BD;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.utad.david.task_3_fragments_lists.Data.ConvertData.ConvertStringToArrayList;
import com.utad.david.task_3_fragments_lists.Data.DAO.CommunitiesDAO;
import com.utad.david.task_3_fragments_lists.Data.DAO.LessonDAO;
import com.utad.david.task_3_fragments_lists.Data.DAO.NotesDAO;
import com.utad.david.task_3_fragments_lists.Data.DAO.NotificationDAO;
import com.utad.david.task_3_fragments_lists.Data.DAO.TeacherDAO;
import com.utad.david.task_3_fragments_lists.Data.DAO.UserDAO;
import com.utad.david.task_3_fragments_lists.Model.Communities;
import com.utad.david.task_3_fragments_lists.Model.Lesson;
import com.utad.david.task_3_fragments_lists.Model.Notes;
import com.utad.david.task_3_fragments_lists.Model.Notifications;
import com.utad.david.task_3_fragments_lists.Model.Teacher;
import com.utad.david.task_3_fragments_lists.Model.User;
import com.utad.david.task_3_fragments_lists.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
La anotacion Database indica que esta clase es una base de datos con las entidades definidas y en este caso es la version 7.
La anotacion TypeConverters hace referencia a una clase propia que convierte datos de las entidades que no se pueden insertar
directamente en la base de datos.
 */

@Database(entities = {Notifications.class, User.class, Notes.class, Teacher.class, Lesson.class, Communities.class}, version = 7)
@TypeConverters(ConvertStringToArrayList.class)
public abstract class BDUtad extends RoomDatabase {

    public abstract UserDAO userDAO();

    public abstract TeacherDAO teacherDAO();

    public abstract NotificationDAO notificationDAO();

    public abstract NotesDAO notesDAO();

    public abstract LessonDAO lessonDAO();

    public abstract CommunitiesDAO communitiesDAO();

    public static BDUtad INSTANCE;

    /*
    Singelton de la base de datos para asegurarnos que solo va a ver una instancia de esta en nuestra aplicacion
     */

    public static BDUtad getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BDUtad.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BDUtad.class, "user_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(callBackDataNotes)
                            .addCallback(callBackDataNotification)
                            .addCallback(callBackDataTeacher)
                            .addCallback(callBackDataLesson)
                            .addCallback(callBackDataCommunities)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /*
    Los callbacks son para llamar a sus respectivas asyncTask para cargar datos en nuestra base de datos.
     */

    static Callback callBackDataNotification = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            (new NotificationsAsyncTask(INSTANCE)).execute();
        }
    };

    static Callback callBackDataNotes = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            (new NotesAsyncTask(INSTANCE)).execute();
        }
    };

    static Callback callBackDataTeacher = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            (new TeacherAsyncTask(INSTANCE)).execute();
        }
    };

    static Callback callBackDataLesson = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            (new LessonAsyncTask(INSTANCE)).execute();
        }
    };

    static Callback callBackDataCommunities = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            (new CommunitiesAsyncTask(INSTANCE)).execute();
        }
    };

    /*
        Estas asyncTasks lo que hacen es crear objectos de Notifications,Notea,Teacher,Lesson y Communities (Nuestras entidades),
        una vez creados todos los añadimos a una Lista, borramos la tabla de la entidad e insertamos dicha lista a la tabla
        correspondiente.
    */


    private static class NotificationsAsyncTask extends AsyncTask<Void, Void, Void> {
        public NotificationDAO notificationsDAO;

        public NotificationsAsyncTask(BDUtad bdUtad) {
            notificationsDAO = bdUtad.notificationDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Notifications notifications1 = new Notifications("2018/08/28", "David", "New Note");
            Notifications notifications2 = new Notifications("2018/09/06", "Pablo", "New Event");
            Notifications notifications3 = new Notifications("2018/09/10", "Sebas", "New work");
            Notifications notifications4 = new Notifications("2018/10/15", "Nacho", "New Note");
            List<Notifications> data = new ArrayList<>();
            data.add(notifications1);
            data.add(notifications2);
            data.add(notifications3);
            data.add(notifications4);
            notificationsDAO.deleteAllNotifications();
            notificationsDAO.insertAllNotifications(data);
            return null;
        }
    }

    private static class NotesAsyncTask extends AsyncTask<Void, Void, Void> {
        public NotesDAO notesDAO;

        public NotesAsyncTask(BDUtad bdUtad) {
            notesDAO = bdUtad.notesDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Notes notes1 = new Notes("2018/11/18", "Android", "Listas", "9");
            Notes notes2 = new Notes("2018/11/09", "IOS", "Tablas", "8.5");
            Notes notes3 = new Notes("2018/10/28", "Procesos", "Semáforos", "6.5");
            Notes notes4 = new Notes("2018/10/19", "Ingles", "Reading", "7.5");
            Notes notes5 = new Notes("2018/10/14", "Acceso a datos", "Hibernate", "10");
            Notes notes6 = new Notes("2018/10/10", "Empresa", "Plan de empresa", "10");
            Notes notes7 = new Notes("2018/09/28", "GG Empresarial", "Odoo", "6.5");
            List<Notes> data = new ArrayList<>();
            data.add(notes1);
            data.add(notes2);
            data.add(notes3);
            data.add(notes4);
            data.add(notes5);
            data.add(notes6);
            data.add(notes7);
            notesDAO.deleteAllNotes();
            notesDAO.insertAllNotes(data);
            return null;
        }
    }

    private static class TeacherAsyncTask extends AsyncTask<Void, Void, Void> {
        public TeacherDAO teacherDAO;

        public TeacherAsyncTask(BDUtad bdUtad) {
            teacherDAO = bdUtad.teacherDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<String> subject1 = new ArrayList<>();
            subject1.add("Acceso a datos");
            subject1.add("Base de datos");
            subject1.add("Lenguaje de marcas");
            ArrayList<String> subject2 = new ArrayList<>();
            subject2.add("Desarollo de interfaces");
            subject2.add("Programación");
            subject2.add("Sistemas Informaticos");
            ArrayList<String> subject3 = new ArrayList<>();
            subject3.add("Ingles G.Superior");
            subject3.add("Ingles G.Medio");
            subject3.add("Ingles Bachillerato");
            ArrayList<String> subject4 = new ArrayList<>();
            subject4.add("Servicios y Procesos");
            subject4.add("Programación");
            subject4.add("Entornos del desarollo");
            ArrayList<String> subject5 = new ArrayList<>();
            subject5.add("Empresa");
            subject5.add("Fol");
            subject5.add("Ingles");
            ArrayList<String> subject6 = new ArrayList<>();
            subject6.add("S.G.Empresarial");
            subject6.add("Entornos del desarollo");
            subject6.add("Programaciòn");
            ArrayList<String> subject7 = new ArrayList<>();
            subject7.add("Ios");
            subject7.add("Programacion");
            subject7.add("Base de datos");

            Teacher teacher1 = new Teacher("Jaime", "Latorre", R.drawable.jaime, subject1, "Experto en SQL", "jaime.latorre@utad.com");
            Teacher teacher2 = new Teacher("David", "Jardon", R.drawable.david, subject2, "Experto en Android", "david.jardon@utad.com");
            Teacher teacher3 = new Teacher("Cristina", " Espinosa", R.drawable.cristina, subject3, "Profesora nativa", "cristina.espinosa@utad.com");
            Teacher teacher4 = new Teacher("Pedro", "Camacho", R.drawable.pedro, subject4, "Experto en POO", "pedro.camacho@utad.com");
            Teacher teacher5 = new Teacher("Meritxel", "Bretos", R.drawable.meritxel, subject5, "Emprendedora", "meritxel.bretos@utad.com");
            Teacher teacher6 = new Teacher("Laura", "Jaen", R.drawable.laura, subject6, "Experiencia en consultoras", "laura.jaen@utad.com");
            Teacher teacher7 = new Teacher("Carlos", "Jimenez", R.drawable.ic_person_outline_black_24dp, subject7, "Experto en Ios", "carlos.jimenez@utad.com");

            List<Teacher> data = Arrays.asList(teacher1, teacher2, teacher3, teacher4, teacher5, teacher6, teacher7);
            teacherDAO.deleteAllTeachers();
            teacherDAO.insertAllTeacher(data);
            return null;
        }
    }

    private static class LessonAsyncTask extends AsyncTask<Void, Void, Void> {
        public LessonDAO lessonDAO;

        public LessonAsyncTask(BDUtad bdUtad) {
            lessonDAO = bdUtad.lessonDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<String> stadistic1 = new ArrayList<>();
            stadistic1.addAll(Arrays.asList(new String[]{"25", "David", "8.1", "8.5", "8"}));
            ArrayList<String> estadisticas2 = new ArrayList<>();
            estadisticas2.addAll(Arrays.asList(new String[]{"27", "Meritxel", "7.6", "7.4", "15"}));
            ArrayList<String> estadisticas3 = new ArrayList<>();
            estadisticas3.addAll(Arrays.asList(new String[]{"34", "Laura", "9.1", "8.1", "18"}));
            ArrayList<String> estadisticas4 = new ArrayList<>();
            estadisticas4.addAll(Arrays.asList(new String[]{"21", "Pedro", "6.3", "7.2", "7"}));
            ArrayList<String> estadisticas5 = new ArrayList<>();
            estadisticas5.addAll(Arrays.asList(new String[]{"18", "Jaime", "8.4", "8.1", "9"}));
            ArrayList<String> estadisticas6 = new ArrayList<>();
            estadisticas6.addAll(Arrays.asList(new String[]{"32", "Cristina", "8.3", "7.4", "17"}));
            ArrayList<String> estadisticas7 = new ArrayList<>();
            estadisticas7.addAll(Arrays.asList(new String[]{"12", "Carlos", "7.2", "8.2", "4"}));

            Lesson lesson1 = new Lesson("DINT", R.drawable.dint, "2018", "Desarrollo de interfaces", estadisticas7);
            Lesson lesson2 = new Lesson("EIEM", R.drawable.eiem, "2018", "Empresa e iniciativa emprendedora", stadistic1);
            Lesson lesson3 = new Lesson("SGEM", R.drawable.sgem, "2018", "Sistemas de gestión empresarial", estadisticas2);
            Lesson lesson4 = new Lesson("PSPR", R.drawable.pspr, "2018", "Programación de servicios y procesos", estadisticas3);
            Lesson lesson5 = new Lesson("ADAT", R.drawable.acceso_datos, "2018", "Acceso a datos", estadisticas4);
            Lesson lesson6 = new Lesson("ITGS", R.drawable.english, "2018", "Inglés técnico de grado superior", estadisticas5);
            Lesson lesson7 = new Lesson("PMDM", R.drawable.pmdm, "2018", "Programación en dispositivos multimedia", estadisticas6);

            List<Lesson> data = Arrays.asList(lesson1, lesson2, lesson3, lesson4, lesson5, lesson6, lesson7);
            lessonDAO.deleteAllLesson();
            lessonDAO.insertAllLesson(data);
            return null;
        }
    }

    private static class CommunitiesAsyncTask extends AsyncTask<Void, Void, Void> {
        public CommunitiesDAO communitiesDAO;

        public CommunitiesAsyncTask(BDUtad bdUtad) {
            communitiesDAO = bdUtad.communitiesDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<String> datos1 = new ArrayList<>();
            datos1.addAll(Arrays.asList(new String[]{"19", "3", "Programame", "Android App", "****"}));
            ArrayList<String> datos2 = new ArrayList<>();
            datos2.addAll(Arrays.asList(new String[]{"32", "2", "SouthSummit", "IOS App", "*****"}));
            ArrayList<String> datos3 = new ArrayList<>();
            datos3.addAll(Arrays.asList(new String[]{"26", "1", "CompanyDay", "WebApp", "***"}));
            ArrayList<String> datos4 = new ArrayList<>();
            datos4.addAll(Arrays.asList(new String[]{"14", "3", "Hackaton", "DatabaseApp", "**"}));

            Communities communities1 = new Communities("Desarrollo de Software", R.drawable.desarrollo_software, "Dani", "Desarrollo de software", "Descripción Desarrollo de Software", "desarrollosoftwareu-tad@gmail.com", datos1);
            Communities communities2 = new Communities("Realidad Virtual", R.drawable.vr, "Fanso", "Desarrollo y diseño para VR", "Descripción Realidad Virtual", "realidadvirtualu-tad@gmail.com", datos2);
            Communities communities3 = new Communities("BigData", R.drawable.big_data, "Juan", "Funcionamiento de bigData", "Descripción BigData", "bigdatau-tad@gmail.com", datos3);
            Communities communities4 = new Communities("Ciberseguridad", R.drawable.ciberseguridad, "Pedro", "Ciberseguridad", "Descripción Ciberseguridad", "ciberseguridadu-tad@gmail.com", datos4);


            List<Communities> data = Arrays.asList(communities1, communities2, communities3, communities4);
            communitiesDAO.deleteAllCommunities();
            communitiesDAO.insertAllCommunities(data);
            return null;
        }
    }
}
