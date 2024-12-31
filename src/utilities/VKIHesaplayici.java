package utilities;

public class VKIHesaplayici {

    /**
     * VKI hesaplama metodu.
     * @param boy Boy (metre cinsinden)
     * @param kilo Kilo (kg cinsinden)
     * @return VKI değeri (double)
     */
    public static double hesapla(double boy, double kilo) {
        if (boy <= 0 || kilo <= 0) {
            throw new IllegalArgumentException("Boy ve kilo sıfırdan büyük olmalıdır.");
        }
        return kilo / (boy * boy);
    }

    /**
     * VKI değerine göre bilgilendirme mesajı döner.
     * @param vki Hesaplanan VKI değeri
     * @return VKI sonucu açıklaması
     */
    public static String bilgiMesaji(double vki) {
        if (vki < 18.5) {
            return "Düşük Kilolu: VKI değeri 18.5'dan az.";
        } else if (vki < 25) {
            return "Sağlıklı Aralık: VKI değeri 18.5 ila 24.9 arasında.";
        } else if (vki < 30) {
            return "Fazla Kilolu: VKI değeri 25 ila 29.9 arasında.";
        } else if (vki < 35) {
            return "1. Sınıf Obezite: VKI değeri 30 ila 34.9 arasında.";
        } else if (vki < 40) {
            return "2. Sınıf Obezite: VKI değeri 35 ila 39.9 arasında.";
        } else if (vki < 50) {
            return "3. Sınıf Obezite (Morbid Obez): VKI değeri 40 ila 49.9 arasında.";
        } else {
            return "Süper Obez: VKI değeri 50 veya daha fazla.";
        }
    }
}
