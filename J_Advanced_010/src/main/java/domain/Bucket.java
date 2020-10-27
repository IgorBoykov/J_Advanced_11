package domain;

import java.util.Date;

public class Bucket {

	private int id;
	private int user_id;
	private int product_id;
	private Date puchase_date;

	public Bucket(int id, int user_id, int product_id, Date puchase_date) {
		this.id = id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.puchase_date = puchase_date;
	}

	public Bucket(int user_id, int product_id, Date puchase_date) {
		this.user_id = user_id;
		this.product_id = product_id;
		this.puchase_date = puchase_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public Date getPuchase_date() {
		return puchase_date;
	}

	public void setPuchase_date(Date puchase_date) {
		this.puchase_date = puchase_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + product_id;
		result = prime * result + ((puchase_date == null) ? 0 : puchase_date.hashCode());
		result = prime * result + user_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bucket other = (Bucket) obj;
		if (id != other.id)
			return false;
		if (product_id != other.product_id)
			return false;
		if (puchase_date == null) {
			if (other.puchase_date != null)
				return false;
		} else if (!puchase_date.equals(other.puchase_date))
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bucket [id=" + id + ", user_id=" + user_id + ", product_id=" + product_id + ", puchase_date="
				+ puchase_date + "]";
	}

}
