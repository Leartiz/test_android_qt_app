#include "androidtoast.h"

#include <QDebug>
#include <QCoreApplication>

#ifdef Q_OS_ANDROID
    #include <QJniObject>
    #include <QJniEnvironment>
#endif

namespace lez
{
    AndroidToast::AndroidToast(QObject *parent)
        : QObject{ parent }
    {}

    void AndroidToast::showToast(const QString &message)
    {
#ifdef Q_OS_ANDROID
    const auto isActivityCtx = QNativeInterface::QAndroidApplication::isActivityContext();
    qDebug() << "isActivityContext:" << isActivityCtx;

    // ***

    QJniObject javaMessage = QJniObject::fromString(message);
    QNativeInterface::QAndroidApplication::runOnAndroidMainThread([javaMessage]() {
       QJniObject activity = QNativeInterface::QAndroidApplication::context();
       activity.callStaticMethod<void>(
            "ru/leartiz/StickyBannerAdActivity",
            "showToast",
            "(Landroid/content/Context;Ljava/lang/String;)V",
            activity.object<jobject>(),
            javaMessage.object<jstring>());
    }).waitForFinished();
#else
    qDebug() << "AndroidToast:" << message;
#endif
    }
}


