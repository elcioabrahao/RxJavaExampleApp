package com.trantordev.rxjavaexampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstExample()

//        createFromArray().subscribe{
//            arr -> println("Recebendo da array: "+ Arrays.toString(arr))
//        }

//        createFromIterable().subscribe{
//            a -> println("Recebendo da iterable: "+ a)
//        }

//        createFromRange().subscribe{
//            a -> println("Recebendo da range: "+ a)
//        }

//        createFromInterval().subscribe{
//            a -> println("Recebendo da range: "+ a)
//        }

//        createFromTimer().subscribe{
//            a -> println("Recebendo Esta pronto em: "+ a)
//        }

//        createTakeLast().subscribe{
//            a -> println("Recebendo take last: "+ a)
//        }

//        createTake().subscribe{
//            a -> println("Recebendo take: "+ a)
//        }

//        createTimeout("elcio").subscribe(
//                {
//                   name -> println("Timeout Nome: "+name)
//                },{
//                    throwable -> println("Recebendo Timeout Erro: "+throwable.javaClass.simpleName)
//                }
//        )
//        createTimeout("aaa").subscribe(
//                {
//                    name -> println("Recebendo Timeout Nome: "+name)
//                },{
//            throwable -> println("Recebendo Timeout Erro: "+throwable.javaClass.simpleName)
//        }
//        )

//        createFromDistinct().subscribe(
//                {
//                    value -> println("Recebendo Valor é: "+value)
//                },{
//            throwable -> println("Recebendo Erro: "+throwable.javaClass.simpleName)
//                }
//        )

//        createStartWith().subscribe(
//                {
//                    name -> println("Recebendo Nome é: "+name)
//                },{
//            throwable -> println("Recebendo Erro: "+throwable.javaClass.simpleName)
//        }
//        )

//        createMerge().subscribe(
//                {
//                    a -> println("Recebendo Valor é: "+a)
//                },{
//            throwable -> println("Recebendo Erro: "+throwable.javaClass.simpleName)
//        }
//        )

//        createConcat().subscribe(
//                {
//                    a -> println("Recebendo Valor é: "+a)
//                },{
//            throwable -> println("Recebendo Erro: "+throwable.javaClass.simpleName)
//        }
//        )

//        createZipWith().subscribe(
//                {
//                    a -> println("Recebendo Nome é: "+a)
//                },{
//            throwable -> println("Recebendo Erro: "+throwable.javaClass.simpleName)
//        }
//        )

//        createMap().subscribe(
//                {
//                    a -> println("Recebendo Nome é: "+a)
//                },{
//            throwable -> println("Recebendo Erro: "+throwable.javaClass.simpleName)
//        }
//        )

//        createFlatMap().subscribe(
//                {
//                    a -> println("Recebendo Nome é: "+a)
//                },{
//            throwable -> println("Recebendo Erro: "+throwable.javaClass.simpleName)
//        }
//        )

//        val professor = PublishSubject.create<String>()
//
//        professor.subscribe(getFirstStudent())
//        professor.onNext("kotlin")
//        professor.onNext("java")
//        professor.onNext("c++")
//
//        professor.subscribe(getLateStudent())
//        professor.onNext("scala")
//        professor.onComplete()


//        val professor = ReplaySubject.create<String>()
//
//        professor.subscribe(getFirstStudent())
//        professor.onNext("kotlin")
//        professor.onNext("java")
//        professor.onNext("c++")
//
//        professor.subscribe(getLateStudent())
//        professor.onNext("scala")
//        professor.onComplete()

//        val professor = BehaviorSubject.create<String>()
//
//        professor.subscribe(getFirstStudent())
//        professor.onNext("kotlin")
//        professor.onNext("java")
//        professor.onNext("c++")
//
//        professor.subscribe(getLateStudent())
//        professor.onNext("scala")
//        professor.onComplete()

        val professor = AsyncSubject.create<String>()

        professor.subscribe(getFirstStudent())
        professor.onNext("kotlin")
        professor.onNext("java")
        professor.onNext("c++")

        professor.subscribe(getLateStudent())
        professor.onNext("scala")
        professor.onComplete()

    }


    private fun firstExample(){

        val dataStream = Observable.just(10,20,30,40)

        val dataObserver = object: Observer<Int> {

            override fun onComplete() {
                println("Todos os dados recebidos...")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Int) {
                println("Novo dado recebido: "+t)
            }

            override fun onError(e: Throwable) {
                println("Erro: "+e.printStackTrace())
            }
        }

        dataStream.safeSubscribe(dataObserver)

    }

    private fun createFromArray(): Observable<Array<Int>>{
        return Observable.fromArray(arrayOf(1,5,7,9))
    }

    private fun createFromIterable(): Observable<Int>{
        return Observable.fromIterable(mutableListOf(2,4,7))
    }

    private fun createFromRange(): Observable<Int>{
        return Observable.range(40,10).repeat(3)
    }

    private fun createFromInterval(): Observable<Long>{
        return Observable.interval(1,TimeUnit.SECONDS).takeWhile{value -> value < 20}
    }

    private fun createFromTimer(): Observable<Long>{
        return Observable.timer(5,TimeUnit.SECONDS)
    }

    private fun createFilter(): Observable<Int>{
        return Observable.just(5,10,30,6).filter{x -> x > 10}
    }

    private fun createTakeLast(): Observable<Int>{
        return Observable.just(5,10,30,6).takeLast(2)
    }

    private fun createTake(): Observable<Int>{
        return Observable.just(5,10,30,6).take(2)
    }

    private fun createTimeout(name: String): Observable<String>{
        return Observable.fromCallable{
            if(name.equals("elcio"))
                Thread.sleep(150)
            name
        }.timeout(100,TimeUnit.MILLISECONDS)
    }

    private fun createFromDistinct(): Observable<Int>{
        return Observable.just(1,2,2,2,4,5,5).distinct()
    }

    private fun createStartWith(): Observable<String>{
        return Observable.just("elcio","valeira","lara").startWith("Cleide")
    }

    private fun createMerge(): Observable<Int>{
        val firstStream = Observable.just(1,2,3)
        val secondStream = Observable.just(4,5,6)
        return firstStream.mergeWith(secondStream)
    }

    private fun createConcat(): Observable<Int>{
        val firstStream = Observable.just(1,2,3)
        val secondStream = Observable.just(4,5,6)
        return secondStream.concatWith(firstStream)
    }

    private fun createZipWith(): Observable<String>{
        val firstNames = Observable.just("Elcio","Valeria","Lara")
        val lastNames = Observable.just("Abrahao","Rett","Simioni")
        return firstNames.zipWith(lastNames, BiFunction{first,last ->
            first + " " + last
        })
    }

    private fun createMap(): Observable<Int>{
        return Observable.just(1,2,3,4,5)
                .map{ value -> value * 10

        }
    }

    private fun createFlatMap(): Observable<String>{
        return Observable.just(1,1,0)
                .flatMap{ id -> getName(id) }
    }

    private fun getName(id: Int): Observable<String>{
        val names = arrayOf("Elcio","Valeria","Lara")
        return Observable.just(names[id])
    }

    private fun getFirstStudent(): Observer<String>{
        return object: Observer<String>{
            override fun onComplete() {
                println("Aula terminou")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                println("Primeiro estudante Professor nos ensinou: "+t)
            }

            override fun onError(e: Throwable) {
                println("Error: ")
            }
        }
    }

    private fun getLateStudent(): Observer<String>{
        return object: Observer<String>{
            override fun onComplete() {
                println("Aula terminou")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                println("Segundo estudante Professor nos ensinou: "+t)
            }

            override fun onError(e: Throwable) {
                println("Error: ")
            }
        }
    }



}
