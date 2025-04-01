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
        
    public:
        void setSize(const QSize value);
        void setPosition(const QPoint value);
        void setAdUnitId(const QString& value);

    signals:
        void sizeChanged(QSize value);
        void positionChanged(QPoint value);
        void adUnitIdChanged(QString value);

    private:
        QSize m_size;
        QPoint m_position;
        QString m_adUnitId;
    };
}

#endif // ANDROIDBANNERAD_H
