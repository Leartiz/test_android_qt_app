#ifndef ANDROIDTOAST_H
#define ANDROIDTOAST_H

#include <QObject>

namespace lez
{
    class AndroidToast : public QObject
    {
        Q_OBJECT
    public:
        explicit AndroidToast(QObject *parent = nullptr);
        Q_INVOKABLE void showToast(const QString &message);
    };
}


#endif // ANDROIDTOAST_H
