package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@ElementCollection
	@CollectionTable(name = "authors", joinColumns = @JoinColumn(name = "book_id"))
	@Column(name = "author")
	private Set<String> authors = new HashSet<>();

	@Column(name = "publish_year")
	private int publishYear;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private Set<Loan> loans = new HashSet<>();

	public Book() {
	}

	public Book(String title, Set<String> authors, int publishYear) {
		this.title = title;
		this.authors = authors;
		this.publishYear = publishYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<String> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<String> authors) {
		this.authors = authors;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	public Set<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Set<Loan> loans) {
		this.loans = loans;
	}

	public void addLoan(Loan loan) {
		loans.add(loan);
	}

	public void removeLoan(Loan loan) {
		loans.remove(loan);
	}
}
