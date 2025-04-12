const url = "http://localhost:8080/task/user/1";
const taskList = document.getElementById("tasks");
const loading = document.getElementById("loading");
const form = document.getElementById("taskForm");
const input = document.getElementById("newTaskInput");

function hideloader() {
    loading.style.display = "none";
}

function renderTasks(tasks) {
    taskList.innerHTML = "";

    tasks.forEach((task, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${index + 1}</td>
            <td contenteditable="true" onblur="updateTask(${task.id}, this.innerText)">${task.description}</td>
            <td>${task.user.username}</td>
            <td>
                <button class="btn btn-danger btn-sm" onclick="deleteTask(${task.id})">Excluir</button>
            </td>
        `;

        taskList.appendChild(row);
    });
}

async function getTasks() {
    try {
        const response = await fetch(url);
        const data = await response.json();
        hideloader();
        renderTasks(data);
    } catch (error) {
        console.error("Erro ao carregar tarefas:", error);
    }
}

async function deleteTask(id) {
    try {
        await fetch(`http://localhost:8080/task/${id}`, {
            method: "DELETE"
        });
        getTasks(); // Atualiza lista
    } catch (error) {
        console.error("Erro ao deletar tarefa:", error);
    }
}

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const task = {
        description: input.value,
        user: {
            id: 1
        }
    };

    try {
        await fetch("http://localhost:8080/task", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(task)
        });

        input.value = "";
        getTasks(); // Atualiza lista
    } catch (error) {
        console.error("Erro ao adicionar tarefa:", error);
    }
});

async function updateTask(id, newDescription) {
    const updatedTask = {
        id: id,
        description: newDescription,
        user: {
            id: 1
        }
    };

    try {
        await fetch(`http://localhost:8080/task`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(updatedTask)
        });
    } catch (error) {
        console.error("Erro ao atualizar tarefa:", error);
    }
}

getTasks();
