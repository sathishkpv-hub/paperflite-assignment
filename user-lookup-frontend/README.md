# User Lookup Frontend (React + TypeScript + Redux Toolkit)

Scripts:

- `npm run dev`: start Vite dev server (proxying `/api` to `http://localhost:8081`)
- `npm run build`: build for production
- `npm run preview`: preview production build

Project structure:

- `src/store`: Redux store setup
- `src/services`: Axios client and API functions
- `src/features/users`: Redux slice, types, and UI for user lookup

Development API:

- GET `/api/auth/getUser/:username` → returns user details. Example username: `developer`.
