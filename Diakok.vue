<template>
  <div class="card shadow">
    <div class="card-header bg-primary text-white">
      <h5>Diákok Listája</h5>
    </div>

    <form class="card-body" @submit.prevent="addStudent">
      <div class="mb-3">
        <input v-model="student.name" type="text" class="form-control mb-2" placeholder="Diák neve pl. Békés Csaba" />
        <input v-model="student.class" type="text" class="form-control" placeholder="Osztály pl. 12X" />
      </div>
      <button type="submit" class="btn btn-primary w-100">Hozzáadás</button>
    </form>

    <ul class="list-group mt-3">
      <li v-for="(s, i) in students" :key="i" class="list-group-item d-flex justify-content-between align-items-center">
        <div>
          <strong>{{ s.name }}</strong>
          <div class="text-muted">{{ s.class }}</div>
        </div>
        <button class="btn btn-danger btn-sm" @click="removeStudent(i)">Törlés</button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      students: [],
      student: {
        name: '',
        class: ''
      }
    }
  },
  created() {
    this.fetchStudents()
  },
  methods: {
    async fetchStudents() {
      const res = await fetch('http://localhost:8000/api/students')
      const data = await res.json()
      this.students = data
    },
    async addStudent() {
      if (this.student.name && this.student.class) {
        const res = await fetch('http://localhost:8000/api/students', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
          },
          body: JSON.stringify(this.student)
        })

        if (res.ok) {
          const newStudent = await res.json()
          this.students.push(newStudent)
          this.student = { name: '', class: '' }
        }
      }
    },
    async removeStudent(index) {
      const student = this.students[index]
      const res = await fetch(`http://localhost:8000/api/students/${student.id}`, {
        method: 'DELETE'
      })

      if (res.ok) {
        this.students.splice(index, 1)
      }
    }
  }
}

</script>
