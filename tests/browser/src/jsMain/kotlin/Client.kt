import browser.LaunchListQuery
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.promise
import kotlin.js.Date

fun main() {
  logd("main()")
  GlobalScope.promise {
    delay(2000)
    val apolloClient = ApolloClient.Builder()
        .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
        .build()
    val response = apolloClient.query(LaunchListQuery(Optional.Absent)).execute()
    logd(response.data)
  }
}

private fun logd(message: Any?) {
  console.log(Date().toString() + " - %o", message)
}
