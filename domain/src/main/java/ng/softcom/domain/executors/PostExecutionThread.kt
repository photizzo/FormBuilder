package ng.softcom.domain.executors

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}