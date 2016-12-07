import com.bridgecanada.utils.HexConversions._

val c1 = "4664dc0697bbfe69330715079ba6c23d2b84de4f908d7d34aace968b64f3df75"
val c2 = "517ecc05c3bdea3b33570e1bd897d5307bd0916b8d826b35b78bbb8d74e2c73b"
val p1 = "43727970746f6772617068792043727970746f6772617068792043727970746f"
println(p1.readable)
(c1 ^ c2 ^ p1).readable



