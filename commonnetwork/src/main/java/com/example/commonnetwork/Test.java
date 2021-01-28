package com.example.commonnetwork;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.commonnetwork.bean.Student;


import java.util.ArrayList;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;


public class Test {
    public List<Student> list = new ArrayList<>();

    @SuppressLint("CheckResult")
    public void getData() {
        Student student = new Student();
        student.name = "曹老板";
        student.age = 1;
        list.add(student);

        Observer<Student>observer=new Observer<Student>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Student s) {

                Log.e("rxjava",s.name);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        Observable.fromArray(list).flatMap(new Function<List<Student>, ObservableSource<Student>>() {
            @Override
            public ObservableSource<Student> apply(List<Student> students) throws Exception {
                for (int i=0;i<students.size();i++){

                    return Observable.fromArray(students.get(i));
                }

                return  null;
            }
        }).subscribe(observer);

    }

}
