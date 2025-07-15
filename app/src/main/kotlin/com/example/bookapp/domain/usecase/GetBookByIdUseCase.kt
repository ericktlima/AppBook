import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.repository.BookRepository
import javax.inject.Inject

class GetBookByIdUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(id: String): Book? {
        return repository.getBookById(id)
    }
}