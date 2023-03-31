//package demo.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "User")
//public class UserEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long Id;
//
//	@Column(name = "taikhoan")
//	private String taikhoan;
//
//	@Column(name = "matkhau")
//	private String matkhau;
//
//	@Column(name = "email")
//	private String email;
//
//	@Column(name = "sdt")
//	private String sdt;
//
//	public Long getId() {
//		return Id;
//	}
//
//	public void setId(Long id) {
//		Id = id;
//	}
//
//	public String getTaikhoan() {
//		return taikhoan;
//	}
//
//	public void setTaikhoan(String taikhoan) {
//		this.taikhoan = taikhoan;
//	}
//
//	public String getMatkhau() {
//		return matkhau;
//	}
//
//	public void setMatkhau(String matkhau) {
//		this.matkhau = matkhau;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getSdt() {
//		return sdt;
//	}
//
//	public void setSdt(String sdt) {
//		this.sdt = sdt;
//	}
//
//}
