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
        Q_PROPERTY(int width READ width WRITE setWidth
                   NOTIFY widthChanged FINAL)
        Q_PROPERTY(int maxHeight READ maxHeight WRITE setMaxHeight
                   NOTIFY maxHeightChanged FINAL)

        // ad unit id
        // x y
        // visibility

    public:
        explicit AndroidBannerAd(QObject *parent = nullptr);

    public:
        int width() const;
        void setWidth(int value);

        int maxHeight() const;
        void setMaxHeight(int value);

    public:
        //void setBannerAdUnitId(const QString& adUnitId);

    signals:
        void widthChanged(int value);
        void maxHeightChanged(int value);

    private:
        void setBannerAdSize(int width, int maxHeight);
        void setBannerAdPosition(float x, float y);

    private:
        QString m_adUnitId;
        QSize m_size;
        QPoint m_position;
    };
}

#endif // ANDROIDBANNERAD_H
