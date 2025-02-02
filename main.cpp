#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include <QJniEnvironment>

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);

    QQmlApplicationEngine engine;
    QObject::connect(&engine, &QQmlApplicationEngine::objectCreationFailed,
                     &app, []() { QCoreApplication::exit(-1); },
    Qt::QueuedConnection);
    engine.loadFromModule("test_android_qt_app", "Main");

    return app.exec();
}
