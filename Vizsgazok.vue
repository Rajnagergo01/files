<template>
  <div class="card shadow border-success">
    <div class="card-header bg-success text-white">
      <h5>Vizsgák Listája</h5>
    </div>

    <form class="card-body" @submit.prevent="addExam">
      <div class="mb-3">
        <label class="form-label">Diák neve</label>
        <input v-model="newExam.studentName" type="text" class="form-control" placeholder="Például: Békés Csaba">

        <label class="form-label mt-2">Terem</label>
        <input v-model="newExam.room" type="text" class="form-control" placeholder="Például: 17">

        <label class="form-label mt-2">Tantárgy</label>
        <input v-model="newExam.subject" type="text" class="form-control" placeholder="Matematika">

        <label class="form-label mt-2">Dátum</label>
        <input v-model="newExam.date" type="date" class="form-control">
      </div>

      <button type="submit" class="btn btn-success w-100">Vizsga hozzáadása</button>
    </form>

    <ul class="list-group mt-3">
      <li v-for="(exam, index) in exams" :key="index" class="list-group-item d-flex justify-content-between align-items-center">
        <span>{{ exam.studentName }} | {{ exam.subject }} | {{ exam.room }} | {{ exam.date }}</span>
        <button class="btn btn-danger btn-sm" @click="removeExam(index)">Törlés</button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      exams: [],
      newExam: {
        studentName: '',
        room: '',
        subject: '',
        date: ''
      }
    }
  },
  created() {
    this.fetchExams()
  },
  methods: {
    async fetchExams() {
      const res = await fetch('http://localhost:8000/api/exams')
      const data = await res.json()
      this.exams = data
    },
    async addExam() {
      const e = this.newExam
      if (e.studentName && e.room && e.subject && e.date) {
        const res = await fetch('http://localhost:8000/api/exams', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
          },
          body: JSON.stringify(this.newExam)
        })

        if (res.ok) {
          const newExam = await res.json()
          this.exams.push(newExam)
          this.newExam = { studentName: '', room: '', subject: '', date: '' }
        }
      }
    },
    async removeExam(index) {
      const exam = this.exams[index]

      const res = await fetch(`http://localhost:8000/api/exams/${exam.id}`, {
        method: 'DELETE'
      })

      if (res.ok) {
        this.exams.splice(index, 1)
      }
    }
  }
}

</script>
