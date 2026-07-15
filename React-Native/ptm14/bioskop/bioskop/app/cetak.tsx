import { router, useLocalSearchParams } from "expo-router";
import { StyleSheet, Text, TouchableOpacity, View } from "react-native";

export default function CetakTiket() {
  // Mengambil data params yang dikirim dari halaman Pesan.tsx
  const { namaPemesan, title, harga, jam, jumlahTiket, nomorKursi, totalBayar } = useLocalSearchParams();

  const hargaTiket = Number(harga || 0);
  const total = Number(totalBayar || 0);

  return (
    <View style={styles.container}>
      <Text style={styles.header}>E-Tiket Anda</Text>
      <Text style={styles.subHeader}>Tunjukkan tiket ini ke petugas bioskop</Text>

      {/* Tampilan Desain Tiket */}
      <View style={styles.ticketCard}>
        <View style={styles.cinemaHeader}>
          <Text style={styles.cinemaName}>STIKOM Cinema</Text>
          <Text style={styles.ticketBadge}>ORIGINAL TICKET</Text>
        </View>

        <View style={styles.divider} />

        <View style={styles.infoRow}>
          <View style={{ flex: 1 }}>
            <Text style={styles.label}>FILM</Text>
            <Text style={styles.movieTitle}>{title || "-"}</Text>
          </View>
        </View>

        <View style={styles.infoGrid}>
          <View style={styles.gridItem}>
            <Text style={styles.label}>NAMA PEMESAN</Text>
            <Text style={styles.value}>{namaPemesan || "-"}</Text>
          </View>
          <View style={styles.gridItem}>
            <Text style={styles.label}>JAM TAYANG</Text>
            <Text style={styles.value}>{jam || "-"}</Text>
          </View>
        </View>

        <View style={styles.infoGrid}>
          <View style={styles.gridItem}>
            <Text style={styles.label}>NOMOR KURSI</Text>
            <Text style={styles.seatValue}>{nomorKursi || "-"}</Text>
          </View>
          <View style={styles.gridItem}>
            <Text style={styles.label}>JUMLAH TIKET</Text>
            <Text style={styles.value}>{jumlahTiket || "0"} Tiket</Text>
          </View>
        </View>

        <View style={styles.divider} />

        <View style={styles.priceRow}>
          <Text style={styles.priceLabel}>Total Pembayaran</Text>
          <Text style={styles.totalPrice}>
            Rp {total.toLocaleString("id-ID")}
          </Text>
        </View>
      </View>

      {/* Tombol Navigasi Kembali */}
      <TouchableOpacity 
        style={styles.button} 
        onPress={() => router.replace("/dashboard")}
      >
        <Text style={styles.buttonText}>Kembali ke Dashboard</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#020617", // Menyesuaikan tema gelap aplikasi Anda
    padding: 20,
    justifyContent: "center",
  },
  header: {
    color: "#ffffff",
    fontSize: 28,
    fontWeight: "bold",
    textAlign: "center",
  },
  subHeader: {
    color: "#94a3b8",
    textAlign: "center",
    marginTop: 5,
    marginBottom: 30,
  },
  ticketCard: {
    backgroundColor: "#0f172a",
    borderRadius: 20,
    padding: 20,
    borderWidth: 1,
    borderColor: "#1e293b",
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.3,
    shadowRadius: 5,
    elevation: 8,
  },
  cinemaHeader: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: 15,
  },
  cinemaName: {
    color: "#38bdf8",
    fontSize: 22,
    fontWeight: "bold",
  },
  ticketBadge: {
    backgroundColor: "#1e293b",
    color: "#38bdf8",
    fontSize: 10,
    fontWeight: "bold",
    paddingHorizontal: 8,
    paddingVertical: 4,
    borderRadius: 6,
    borderWidth: 0.5,
    borderColor: "#38bdf8",
  },
  divider: {
    height: 1,
    backgroundColor: "#1e293b",
    marginVertical: 15,
    borderStyle: "dashed",
  },
  infoRow: {
    marginBottom: 15,
  },
  infoGrid: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginBottom: 15,
  },
  gridItem: {
    flex: 1,
  },
  label: {
    color: "#64748b",
    fontSize: 11,
    fontWeight: "600",
    letterSpacing: 1,
  },
  movieTitle: {
    color: "#ffffff",
    fontSize: 20,
    fontWeight: "bold",
    marginTop: 4,
  },
  value: {
    color: "#ffffff",
    fontSize: 15,
    fontWeight: "600",
    marginTop: 4,
  },
  seatValue: {
    color: "#38bdf8",
    fontSize: 18,
    fontWeight: "bold",
    marginTop: 4,
  },
  priceRow: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    marginTop: 5,
  },
  priceLabel: {
    color: "#94a3b8",
    fontSize: 14,
  },
  totalPrice: {
    color: "#4ade80", // Warna hijau untuk sukses/pembayaran
    fontSize: 20,
    fontWeight: "bold",
  },
  button: {
    backgroundColor: "#0284c7",
    padding: 16,
    borderRadius: 14,
    alignItems: "center",
    marginTop: 30,
  },
  buttonText: {
    color: "#ffffff",
    fontWeight: "bold",
    fontSize: 16,
  },
});