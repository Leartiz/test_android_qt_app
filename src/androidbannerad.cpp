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

    // -------------------------------------------------------------------

    int AndroidBannerAd::getWidth() const
    {
        return m_size.width();
    }

    int AndroidBannerAd::getMaxHeight() const
    {
        return m_size.height();
    }

    // -------------------------------------------------------------------

    QSize AndroidBannerAd::getSize() const
    {
        return m_size;
    }

    QPoint AndroidBannerAd::getPosition() const
    {
        return m_position;
    }
    
    QString AndroidBannerAd::getAdUnitId() const
    {
        return m_adUnitId;
    }

    bool AndroidBannerAd::getIsVisible() const
    {
        return m_isVisible;
    }
        
    // -------------------------------------------------------------------

    void AndroidBannerAd::setIsVisible(bool value)
    {
        if (value) {
            show();
        }
        else {
            hide();
        }
    }

    void AndroidBannerAd::hide()
    {
        if (!m_isVisible)
            return;

#ifdef Q_OS_ANDROID
        QJniObject activity = QNativeInterface::QAndroidApplication::context();
        if (!activity.isValid()) {
            qWarning() << "Activity is not valid";
            return;
        }

        activity.callMethod<void>("hideBannerAd");
#else
        qDebug() << "No ANDROID" << value;
#endif
        m_isVisible = false;
    }

    void AndroidBannerAd::show()
    {
        if (m_isVisible)
            return;

#ifdef Q_OS_ANDROID
        QJniObject activity = QNativeInterface::QAndroidApplication::context();
        if (!activity.isValid()) {
            qWarning() << "Activity is not valid";
            return;
        }

        activity.callMethod<void>("showBannerAd");
#else
        qDebug() << "No ANDROID" << value;
#endif
        m_isVisible = true;
    }

    // -------------------------------------------------------------------

    void AndroidBannerAd::setSize(const QSize value)
    {
        qDebug() << "AndroidBannerAd::setSize";
        qDebug() << value;

#ifdef Q_OS_ANDROID
        QJniObject activity = QNativeInterface::QAndroidApplication::context();
        if (!activity.isValid()) {
            qWarning() << "Activity is not valid";
            return;
        }

        QJniEnvironment env;
        const jclass integerJavaClazz = env.findClass("java/lang/Integer");
        if (!integerJavaClazz) {
            qWarning() << "Unable to find class 'java/lang/Integer'";
            return;
        }

        // ***

        qDebug() << "Create objects 'widthObject' and 'maxHeightObject'";

        const auto widthObject = QJniObject(integerJavaClazz, value.width());
        const auto maxHeightObject = QJniObject(integerJavaClazz, value.height());

        qDebug() << "Objects 'widthObject' and 'maxHeightObject' OK";

        // ***

        if (!widthObject.isValid()) {
            qWarning() << "Failed to create object for width";
            return;
        }
        if (!maxHeightObject.isValid()) {
            qWarning() << "Failed to create object for maxHeight";
            return;
        }

        // ***

        qDebug() << "Attempt to java-call 'setBannerAdSize' method";
        activity.callMethod<void>("setBannerAdSize",
                                  "(Ljava/lang/Integer;Ljava/lang/Integer;)V",

                                  // need to convert it to an object here?
                                  widthObject.object<jobject>(),
                                  maxHeightObject.object<jobject>());
#else
        qDebug() << "No ANDROID" << value;
#endif
        m_size = value;
    }

    void AndroidBannerAd::setPosition(const QPoint value)
    {
#ifdef Q_OS_ANDROID
        if (m_position == value)
            return;

        QJniObject activity = QNativeInterface::QAndroidApplication::context();
        if (!activity.isValid()) {
            qWarning() << "Activity is not valid";
            return;
        }

        activity.callMethod<void>("setBannerAdPosition", "(FF)V",
                                  static_cast<float>(value.x()),
                                  static_cast<float>(value.y()));
#else
        qDebug() << "No ANDROID" << value;
#endif
        m_position = value;
    }

    void AndroidBannerAd::setAdUnitId(const QString& value)
    {
#ifdef Q_OS_ANDROID
        QJniObject adUnitIdObject = QJniObject::fromString(value);
        if (!adUnitIdObject.isValid()) {
            qWarning() << "Failed to create adUnitId object";
            return;
        }

        QJniObject activity = QNativeInterface::QAndroidApplication::context();
        if (!activity.isValid()) {
            qWarning() << "Activity is not valid";
            return;
        }

        activity.callMethod<void>("setBannerAdUnitId",
                                  "(Ljava/lang/String;)V",
                                  adUnitIdObject.object<jstring>());
        m_adUnitId = value;
#else
        qDebug() << "No ANDROID" << value;
#endif
    }

    // -------------------------------------------------------------------

    void AndroidBannerAd::placeAtBottomCenter()
    {
#ifdef Q_OS_ANDROID
        QJniObject activity = QNativeInterface::QAndroidApplication::context();
        if (!activity.isValid()) {
            qWarning() << "Activity is not valid";
            return;
        }

        activity.callMethod<void>("placeBannerAdViewAtBottomCenter");
#else
        qDebug() << "No ANDROID";
#endif
    }

    void AndroidBannerAd::placeAtTopCenter()
    {
#ifdef Q_OS_ANDROID
        QJniObject activity = QNativeInterface::QAndroidApplication::context();
        if (!activity.isValid()) {
            qWarning() << "Activity is not valid";
            return;
        }

        activity.callMethod<void>("placeBannerAdViewAtTopCenter");
#else
        qDebug() << "No ANDROID";
#endif
    }
}



