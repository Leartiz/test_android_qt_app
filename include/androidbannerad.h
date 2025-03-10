#ifndef ANDROIDBANNERAD_H
#define ANDROIDBANNERAD_H

#include <QObject>

namespace lez
{
    class AndroidBannerAd : public QObject
    {
        Q_OBJECT
    public:
        explicit AndroidBannerAd(QObject *parent = nullptr);

    public:
        void setUnitId(const QString& unitId);

    };
}

#endif // ANDROIDBANNERAD_H
