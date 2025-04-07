#ifndef ANDROIDBANNERAD_H
#define ANDROIDBANNERAD_H

#include <QObject>
#include <QString>

#include <QPoint>
#include <QSize>

namespace lez
{
    class AndroidBannerAd : public QObject
    {
        Q_OBJECT

    public:
        Q_PROPERTY(QSize size READ getSize WRITE setSize
                   NOTIFY sizeChanged FINAL)
        Q_PROPERTY(QPoint position READ getPosition WRITE setPosition
                   NOTIFY positionChanged FINAL)
        Q_PROPERTY(bool isVisible READ getIsVisible WRITE setIsVisible
                   NOTIFY isVisibleChanged FINAL)

    public:
        Q_PROPERTY(QString adUnitId READ getAdUnitId WRITE setAdUnitId
                   NOTIFY adUnitIdChanged FINAL)

    public:
        explicit AndroidBannerAd(QObject *parent = nullptr);

    public:
        Q_INVOKABLE int getWidth() const;
        Q_INVOKABLE int getMaxHeight() const;

        Q_INVOKABLE void hide();
        Q_INVOKABLE void show();

    public:
        QSize getSize() const;
        QPoint getPosition() const;
        QString getAdUnitId() const;
        bool getIsVisible() const;
        
    public:
        void setSize(const QSize value);
        void setPosition(const QPoint value);
        void setAdUnitId(const QString& value);
        void setIsVisible(bool value);

    public:
        Q_INVOKABLE void placeAtBottomCenter();
        Q_INVOKABLE void placeAtTopCenter();

    signals:
        void sizeChanged(QSize value);
        void positionChanged(QPoint value);
        void adUnitIdChanged(QString value);
        void isVisibleChanged(bool value);

    private:
        QSize m_size;
        QPoint m_position;
        QString m_adUnitId;
        bool m_isVisible{ true };
    };
}

#endif // ANDROIDBANNERAD_H
