#include "androidbannerad.h"

#include <QDebug>
#include <QCoreApplication>

#ifdef Q_OS_ANDROID
    #include <QJniObject>
    #include <QJniEnvironment>
#endif

namespace lez
{
    AndroidBannerAd::AndroidBannerAd(QObject *parent)
        : QObject{ parent }
        , m_adUnitId{ "demo-banner-yandex" } // copy from `BannerAdActivity`!
        , m_size{ 320, 50 }, m_position{ 0, 0 }
    {
        qDebug() << "ctor 'AndroidBannerAd'";
    }

    void AndroidBannerAd::setBannerAdSize(int width, int maxHeight)
    {
#ifdef Q_OS_ANDROID
        qDebug() << "AndroidBannerAd::setBannerAdSize";

        QJniObject activity = QNativeInterface::QAndroidApplication::context();
        if (!activity.isValid()) {
            qWarning() << "Activity is not valid";
            return;
        }

        QJniEnvironment env;
        jclass integerJavaClazz = env.findClass("java/lang/Integer");
        if (!integerJavaClazz) {
            qWarning() << "Unable to find class 'java/lang/Integer'";
            return;
        }

        // ***
        qDebug() << "Create 'widthObject' and 'maxHeightObject'";

        //const QString integerCtorSignature = "(I)V";
        QJniObject widthObject = QJniObject(integerJavaClazz, width);
        QJniObject maxHeightObject = QJniObject(integerJavaClazz, maxHeight);

        qDebug() << "'widthObject' and 'maxHeightObject' OK";

        if (!widthObject.isValid()) {
            qWarning() << "Failed to create object for width";
            return;
        }
        if (!maxHeightObject.isValid()) {
            qWarning() << "Failed to create object for maxHeight";
            return;
        }

        qDebug() << "attempt to call 'setBannerAdSize' method";
        activity.callMethod<void>("setBannerAdSize",
                                  "(Ljava/lang/Integer;Ljava/lang/Integer;)V",
                                  widthObject.object<jobject>(),
                                  maxHeightObject.object<jobject>()
                                  );
#else
    qDebug() << "setBannerAdSize:"
             << width << maxHeight;
#endif
    }

    void AndroidBannerAd::setBannerAdPosition(float x, float y)
    {

    }

    // -------------------------------------------------------------------

    int AndroidBannerAd::width() const
    {
        return m_size.width();
    }

    void AndroidBannerAd::setWidth(int value)
    {
        m_size.setWidth(value);
        setBannerAdSize(width(), maxHeight());
    }

    int AndroidBannerAd::maxHeight() const
    {
        return m_size.height();
    }

    void AndroidBannerAd::setMaxHeight(int value)
    {
        m_size.setHeight(value);
        setBannerAdSize(width(), maxHeight());
    }
}

