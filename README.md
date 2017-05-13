# EventBus
This class used for notifying part of application to another part that not related to each other, imagine you have one class that want to tell something to UI without knowing anything from that, you can use this project to do that. simply this is one project like EventBus from Jake Wharton or similar projects.

**HOW TO Config:**

You **CAN** customize EventManager object with `Builder` class like follow:

    new EventManager.Builder()
                .setThreadPoolSize(3)
                .setErrorHandler(errorHandler)
                .enableLoggingInDebug()
                .build();
                
* every process in EventManager do in one threadPool that you specify this size in builder class ( default value is 2 ).
* if you face with error in event processing errorHandler will be get it if is not null.
* you can enable Log in Debug mode with `enableLoggingInDebug`. in release Logs will be ignored.


**HOW TO USE:**

For posting your objects you need to use instance method like following:

    EventManager.getInstance().postEvent(ApplicationClass.MAIN_TEST_EVENT_NUM, "data"); // you can pass any data
    
as you can see this method will receive 2 parameter.

1- EventNum: this is your eventKey that you want to send data, this will relate events that you post with onReceive method ( describe later), this must be UNIQUE, if is not you receive wrong data for wrong event number, to prevent issues like this create one class and declare all eventNumber in that class and increment all eventNum. 

2- objects: is an array of data that you want to pass, you can pass many object that separate with ','.

with above code you can pass your data, now you must receive it. in each class that you want to listen on particular event you must use following method to register class to receiving:

    EventManager.getInstance().addEventListener(ApplicationClass.MAIN_TEST_EVENT_NUM, this);
    
with above code your class must be implement `EventListener` to receive messages. be sure to remove listener as your work has been done to optimize eventManager ( for example in activity you must add your listener in `onCreate` or `onStart` and remove it in `onDestroy` or `onStop` ). you can remove listener with :

    EventManager.getInstance().removeEventListener(ApplicationClass.MAIN_TEST_EVENT_NUM, this);
    
`EventListener` has one method that you must implement to proccess your data:

    public void receivedMessage(int id, Object... message)
    
`id` is an eventNum that you specify in `postEvent`
`messages` is an array of data that you post in `postEvent`

if you have heavy process in `receivedMessage` use your own thread to free up eventManager thread.

PS: as your object will receive in threadPool you can't update UI in onReceive method, you must use `Handler` or `runOnUIThread` to do UI stuff.
