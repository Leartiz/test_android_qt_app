#include <QGuiApplication>
#include <QQmlApplicationEngine>

#include "androidtoast.h"
#include "androidbannerad.h"

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);

    QQmlApplicationEngine engine;
    qmlRegisterSingletonInstance<lez::AndroidToast>("AndroidToast", 1, 0,
                                                    "AndroidToast", new lez::AndroidToast());

    qmlRegisterType<lez::AndroidBannerAd>("AndroidBannerAd", 1, 0, "AndroidBannerAd");

    QObject::connect(&engine, &QQmlApplicationEngine::objectCreationFailed,
                     &app, []() { QCoreApplication::exit(-1); },
    Qt::QueuedConnection);
    engine.loadFromModule("test_android_qt_app", "Main");

    return app.exec();
}
