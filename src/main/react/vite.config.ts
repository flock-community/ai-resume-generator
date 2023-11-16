import {defineConfig} from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
    server: {
        proxy: {
            // string shorthand: http://localhost:5173/foo -> http://localhost:4567/foo
            '/api/flocker': 'http://localhost:8080'
        }
    },
    plugins: [react()]
})
