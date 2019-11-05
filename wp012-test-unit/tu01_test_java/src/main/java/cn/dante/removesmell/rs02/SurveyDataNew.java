package cn.dante.removesmell.rs02;

class SurveyDataNew {
    String path; //将数据存到这个路径的文件
    boolean hidden; //这个文件是否要隐藏
    static String prefix="c:/application/data/";
    static String suffix=".dat";

    //根据 t 的类型，设置存放数据的文件的路径和隐藏属性
    void setSavePath(int t) {
        if (t == 0) { //raw data.
            path = prefix+"raw"+suffix;
            hidden = true;
        } else if (t == 1) { //清空数据
            path = "c:/application/data/cleanedUp.dat";
            hidden = true;
        } else if (t == 2) { //处理数据
            path = "c:/application/data/processed.dat";
            hidden = true;
        } else if (t == 3) { //数据可以公布
            path = "c:/application/data/publication.dat";
            hidden = false;
        }
    }
}